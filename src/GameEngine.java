import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;

	public class GameEngine extends Canvas implements Runnable{
	private static final long serialVersionUID = 1L;
	
	public int width = Stage.WIDTH;
    public int height = Stage.HEIGHT;
	public String title;
	
	
	
	int location;
	int num=0;
	int sandbagLocation;
	int sandbagNum = 0;
	boolean firstTime = true;
	int loopAmmount = 1;
	int YAmount = 75;
	int XAmmount = 0;
	
	
	
	
	private boolean running = false;
	static Graphics g;
	private Thread thread;
	private JFrame frame;
	private Keyboard key;
	
	static Player p = new Player(60,60, Stage.WIDTH / 2 - 30, Stage.HEIGHT -60, 5);
	static Bullet b = new Bullet();
	static Sandbag[] sb = new Sandbag[8];
	static Monster[] m = new Monster[48];
	
	public GameEngine() 
	{
		Dimension size = new Dimension(width,height);
		setPreferredSize(size);
		frame = new JFrame();
		key = new Keyboard();
		addKeyListener(key);
	}
	
	public void init(){

		SpriteCache.init();
		
			for (int i = 0; i < m.length; i++) {
				
							
				XAmmount = loopAmmount * 50;
				
				m[i] = new Monster(XAmmount, YAmount);
				
				if(loopAmmount == 12){
					YAmount = YAmount + 75;
					System.out.println("test");
					XAmmount = 60;
					loopAmmount = 0;
				}
				loopAmmount++;
			}
		
		
		for (int i = 0; i < sb.length; i++) {
			
			sandbagLocation = i * 120;
			
			if(firstTime){
				sandbagLocation += 60;
				firstTime = false;
			}
			
			if(sandbagNum == 2){
				sandbagLocation = sandbagLocation +  60;
				sandbagNum = 0;
			}
			
			sb[i] = new Sandbag(sandbagLocation, Stage.HEIGHT - 150);
			sandbagNum++;
		}
				
	}
		
	public void update(){
		key.update();
		p.update();
		b.update();
		
		for (int i = 0; i < sb.length; i++) {
			sb[i].update();
		}
		
		
		for (int i = 0; i < m.length; i++) {
			m[i].update();
		}
		
		
		if(b.LocationY < -60){
			b.shot = false;
			b.LocationY = this.height + 20;
		}
		
	}
	
	public void render(){
		
		BufferStrategy bs = getBufferStrategy();
		if(bs == null){
			createBufferStrategy(2);
			return;
		}
		g = bs.getDrawGraphics();
		//Start Drawing
				
		
		//Game Background
		g.setColor(Color.white);
		g.fillRect(0, 0, width, height);
		//Game Background END
		
		//Player Movement START
		
		if (p.getLocationX() <= width - p.Width && key.right) 
		{
				p.moveRight();
		}
		if (p.getLocationX() >= 0 && key.left)
		{
				p.moveLeft();	
		}
		if (key.space){
			
			if(b.shot == false){
				b.LocationX = p.getLocationX() + 25;
				b.LocationY = p.getLocationY();
				b.shot = true;
			}
		}
		
		g.drawImage(SpriteCache.bullet, b.getLocationX(), b.getLocationY(), null);
		g.drawImage(SpriteCache.player, p.getLocationX(), p.getLocationY(), null);
		
		
		//Player Movement END
		
		//Alien
			
		for (int i = 0; i < m.length; i++) {
	
			g.drawImage(SpriteCache.alien, m[i].LocationX, m[i].LocationY, null);

		}
		
		
		for (int i = 0; i < m.length; i++){
		
			g.drawImage(SpriteCache.alienBullet, m[i].bulletLocationX, m[i].bulletLocationY, null);
		
		}
		
		//Alien End	
		
		
		
		
		//Sand bag
		
		for (int i = 0; i < sb.length; i++) {
			
			g.drawImage(SpriteCache.sandbag[sb[i].state], sb[i].LocationX, sb[i].LocationY, null);

		}
		
		
		
		//Sand bag end
		
		g.setColor(Color.green);
		g.drawString("Life: " + p.Life, 10, 20);
		
		if(p.Life <= 0){
			g.drawImage(SpriteCache.endGame, (Stage.WIDTH / 2) - 120, (Stage.HEIGHT / 2) - 50, null);
			p.LocationX = 5000;
		}
		
		
		
		//End Drawing
		
		g.dispose();
		bs.show();
	}	


	public synchronized void start(){
		
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
		
		
	}
	
	public synchronized void stop(){
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		//This code sets lastTime to the systems time in nano seconds
		long lastTime = System.nanoTime();
		//This code is used in the math to only allow 60 updates a second
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		init();
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns; 
			lastTime = now;
			while (delta >= 1){
				
				render();
				
				update();
				delta--;
			
			}
		}
		
	}
	
	
	public static void main(String[] args) 
	{	
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

