import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GameEngine extends Canvas implements Runnable, Stage {
	private static final long serialVersionUID = 1L;

	public int width = Stage.WIDTH;
	public int height = Stage.HEIGHT;
	public static boolean gameOver = false;
	public String title;

	int location;
	int num = 0;
	int sandbagLocation;
	int sandbagNum = 0;
	boolean firstTime = true;
	int loopAmmount = 1;
	int YAmount = 75;
	int XAmmount = 0;

	private String highScore = "";

	private boolean running = false;
	boolean dev = false;
	static Graphics g;
	private Thread thread;
	private JFrame frame;
	private Keyboard key;

	public GameEngine() {
		Dimension size = new Dimension(width, height);
		setPreferredSize(size);
		frame = new JFrame();
		key = new Keyboard();
		addKeyListener(key);
	}

	public void init() {

		SpriteCache.init();
		monsterArray.add(m[1]);
		if(highScore.equals("")){
			highScore = this.GetHighScoreValue();
		}

		for (int i = 0; i < m.length; i++) {

			XAmmount = loopAmmount * 50;
			monsterArray.add(m[i]);
			m[i] = new Monster(XAmmount, YAmount);

			if (loopAmmount == 12) {
				YAmount = YAmount + 75;
				XAmmount = 60;
				loopAmmount = 0;
			}
			loopAmmount++;
		}

		for (int i = 0; i < sb.length; i++) {

			sandbagLocation = i * 120;

			if (firstTime) {
				sandbagLocation += 60;
				firstTime = false;
			}

			if (sandbagNum == 2) {
				sandbagLocation = sandbagLocation + 60;
				sandbagNum = 0;
			}

			sb[i] = new Sandbag(sandbagLocation, Stage.HEIGHT - 150);
			sandbagNum++;
		}

	}

	public void update() {
		key.update();
		p.update();
		p.move(key);
		b.update(key);
		as.update();
		DevMode();
				
		if(dev){
			p.Life = 9999;
		}

		for (int i = 0; i < sb.length; i++) {
			sb[i].update();
		}

		for (int i = 0; i < m.length; i++) {
			m[i].update();
		}

		if (b.LocationY < -60) {
			b.shot = false;
			b.LocationY = this.height + 20;
		}
	}
	public void render() {

		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(2);
			return;
		}
		g = bs.getDrawGraphics();
		// Start Drawing

		// Game Background
		g.setColor(Color.white);
		g.fillRect(0, 0, width, height);
		as.draw(g);
		b.draw(g);
		p.draw(g);

		for (int i = 0; i < m.length; i++) {
			m[i].draw(g);
		}

		// Sand bag
		for (int i = 0; i < sb.length; i++) {
			sb[i].draw(g);
		}

		// Life counter & score
		g.setColor(Color.red);
		g.setFont(new Font("Comic Sands", Font.PLAIN, 20));
		g.drawString("Life: " + p.Life, 10, 30);
		g.drawString("High Score: " + highScore +" Score: " + p.Score, Stage.WIDTH - 300, 30);

		if (p.Life <= 0 || GameEngine.gameOver) {
			CheckScore();
			g.drawImage(SpriteCache.endGame, (Stage.WIDTH / 2) - 120,
					(Stage.HEIGHT / 2) - 50, null);
			p.LocationX = 5000;
		}
		if (monsterArray.size() <= 1) {
			CheckScore();
			g.drawImage(SpriteCache.endGameWin, (Stage.WIDTH / 2) - 80,
					(Stage.HEIGHT / 2) - 50, null);
			p.LocationX = 5000;
		}

		// End Drawing
		g.dispose();
		bs.show();
	}
	
	public void ReduceScore(){
		if(p.Score > 0){
			p.Score -= 1;
		}
	}
	
	public void CheckScore(){
		
		if (p.Score > Integer.parseInt(highScore.split(":")[1])){
			String name = JOptionPane.showInputDialog("New High Score! What is your name?");
			highScore = name + ":" + p.Score;
			
			File scoreFile = new File("highscore.dat");
			if(!scoreFile.exists())
			{
				try {
					scoreFile.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			FileWriter writeFile = null;
			BufferedWriter writer = null;
			try
			{
				writeFile = new FileWriter(scoreFile);
				writer = new BufferedWriter(writeFile);
				writer.write(this.highScore);
			}
			catch (Exception e){
				
			}
			finally
			{
				try
				{
					if(writer != null)
						writer.close();
				}
				catch (Exception e){
					
				}
			}
		}
	}
	
	public String GetHighScoreValue(){
		
		FileReader readFile = null;
		BufferedReader reader = null;
		try
		{
			readFile = new FileReader("highscore.dat");
			reader = new BufferedReader(readFile);
			return reader.readLine();
		}
		
		catch (Exception e)
		{
			return "Nobody:0";
		}
		finally
		{
			try {
				if(reader != null)
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	void DevMode(){
		if(key.devButtonEnable){
			dev =  true;
		}
		if(key.devButtonDisable){
			dev =  false;
		}
		
	}

	public synchronized void start() {

		running = true;
		thread = new Thread(this, "Display");
		thread.start();

	}

	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// This code sets lastTime to the systems time in nano seconds
		long lastTime = System.nanoTime();
		// This code is used in the math to only allow 60 updates a second
		final double ns = 1000000000.0 / 60.0;//was 60
		double delta = 0;
		int decay = 0;
		init();
		while (running) {
			long now = System.nanoTime();
			
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {

				render();
				update();
				delta--;
				decay++;
				if(decay >= 60){
					ReduceScore();
					decay = 0;
				}
			}
		}

	}

	public static void main(String[] args) {
		GameEngine game = new GameEngine();

		game.frame.setResizable(false);
		game.frame.setTitle("Space Invaders");
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);
		game.frame.setFocusable(true);

		game.start();
	}

}
