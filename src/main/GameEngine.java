package main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import javax.swing.JFrame;

	public class GameEngine extends Canvas implements Runnable, Stage{
	private static final long serialVersionUID = 1L;
	
	public String title;
	
	ArrayList<Alien> alienList = new ArrayList<Alien>();
	
	private boolean running = false;
	static Graphics g;
	private Thread thread;
	private JFrame frame;
	
	public GameEngine() 
	{
		Dimension size = new Dimension(width,height);
		setPreferredSize(size);
		frame = new JFrame();
		addKeyListener(key);
	}
	
	public void init(){
				
	}
		
	public void update(){
		key.update();
		player.update();
	}
	
	
	
	public void render(){
		
		BufferStrategy bs = getBufferStrategy();
		if(bs == null){
			createBufferStrategy(2);
			return;
		}
		
		g = bs.getDrawGraphics();
		//Start Drawing//
		
		//Draw Background		
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);
		
		//Draw Player
		g.setColor(Color.BLUE);
		g.fillRect(player.x, player.y, player.w, player.h);		
		
		//End Drawing//
		
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

