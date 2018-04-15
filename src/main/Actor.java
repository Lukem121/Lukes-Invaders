package main;

import java.awt.Graphics2D;
import java.awt.Rectangle;
//test
public class Actor {
	
	int x;
	int y;
	int vx;
	int vy;
	
	int w;
	int h;

	int speed;
	float xdir;
	
	boolean markedForRemoval = false;
	
	public void paint(Graphics2D g) {
		g.drawImage(SpriteCache.alien, x, y, null);
	}
	
	public void act() {
	}
	
	public int getHeight() {
		return h;
	}
	public int getWidth() {
		return w;
	}
	public void setX(int value) {
		this.x = value;
	}
	public void setY(int value) {
		this.y = value;
	}
	public void setVx(int value) {
		this.vx = value;
	}
	public Rectangle getBounds() {
		return new Rectangle(x,y,w, h);
	}
	public boolean isMarkedForRemoval() {
		return markedForRemoval;
	}

	public void collision(Actor a2) {
		// TODO Auto-generated method stub
		
	}

	
	
}
