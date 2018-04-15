package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class Player extends Actor implements Stage {

	private boolean up,down,left,right;
	private SpaceInvaders si;
	
	public Player(SpaceInvaders si) {
		this.x = width/2 - 30;
		this.y = height - 60;
		this.w = 60;
		this.h = 60;
		this.speed = 4;
		this.si = si;
	}
	
	void update(){
		move();
	}
	
	public void paint(Graphics2D g) {
		g.drawImage(SpriteCache.player, x, y, null);
	}
	
	void move() {
				if (x <= width - w && si.right) 
		{
			x = x + speed;
		}
		if (x >= 0 && si.left)
		{
			x = x - speed;	
		}
		}

	public void collision(Actor a1) {
		// TODO Auto-generated method stub
		
	}


	

}
