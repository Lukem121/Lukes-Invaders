package main;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

//test

public class SpaceInvaders extends Canvas implements Stage, KeyListener {

	private BufferStrategy strategy;
	private long usedTime;
	private SpriteCache spriteCache;
	private ArrayList actors;
	private Player player;
	private boolean[] keys;

	public boolean left, right, space;

	public SpaceInvaders() {
	
		JFrame f = new JFrame("Space Invaders");
		JPanel panel = (JPanel) f.getContentPane();
		setBounds(0, 0, Stage.width, Stage.height);
		panel.setPreferredSize(new Dimension(Stage.width, Stage.height));
		panel.setLayout(null);
		panel.add(this);
		f.setBounds(0, 0, Stage.width, Stage.height);
		f.setVisible(true);
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		f.setResizable(false);
		createBufferStrategy(2);
		strategy = getBufferStrategy();
		setFocusable(true);
		requestFocus();
		f.pack();
		
		addKeyListener(this);
	}

	public void initWorld() {
		spriteCache.init();
		keys = new boolean[256];
		actors = new ArrayList<Actor>();
		for (int i = 0; i < 10; i++) {
			Monster m = new Monster(this);
			m.setX((int) (Math.random() * Stage.width));
			m.setY(i * 20);
			m.setVx((int) (Math.random() * 20 - 10));
			actors.add(m);
		}

		player = new Player(this);
	}

	public void addActor(Actor a) {
		actors.add(a);
	}

	public void updateWorld() {
		
		left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
		space = keys[KeyEvent.VK_SPACE];
		
		int i = 0;
		while (i < actors.size()) {
			Actor m = (Actor) actors.get(i);
			if (m.isMarkedForRemoval()) {
				actors.remove(i);
			} else {
				m.act();
				i++;
			}
		}
		player.update();
	}

	public void checkCollisions() {
		Rectangle playerBounds = player.getBounds();
		for (int i = 0; i < actors.size(); i++) {
			Actor a1 = (Actor) actors.get(i);
			Rectangle r1 = a1.getBounds();
			if (r1.intersects(playerBounds)) {
				player.collision(a1);
				a1.collision(player);
			}
			for (int j = i + 1; j < actors.size(); j++) {
				Actor a2 = (Actor) actors.get(j);
				Rectangle r2 = a2.getBounds();
				if (r1.intersects(r2)) {
					a1.collision(a2);
					a2.collision(a1);
				}
			}
		}
	}

	public void paintWorld() {
		Graphics2D g = (Graphics2D) strategy.getDrawGraphics();
		g.setColor(Color.green);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		for (int i = 0; i < actors.size(); i++) {
			Actor m = (Actor) actors.get(i);
			m.paint(g);
		}
		player.paint(g);
		g.setColor(Color.white);
		if (usedTime > 0)
			g.drawString(String.valueOf(1000 / usedTime) + " fps", 0,
					Stage.height - 50);
		else
			g.drawString("--- fps", 0, Stage.height - 50);
		g.dispose();
		strategy.show();
	}

	public SpriteCache getSpriteCache() {
		return spriteCache;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	
	public void game() {
		usedTime = 1000;
		initWorld();
		while (isVisible()) {
			long startTime = System.currentTimeMillis();
			updateWorld();

			checkCollisions();

			paintWorld();
			
			usedTime = System.currentTimeMillis() - startTime;
			
			//calculate sleep time
			if (usedTime == 0) usedTime = 1;
			int timeDiff = (int) ((1000/usedTime) - 50);
			if (timeDiff > 0) {
				try {
					Thread.sleep(timeDiff/100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
	}

	public static void main(String[] args) {
		SpaceInvaders inv = new SpaceInvaders();
		inv.game();
	}
}
