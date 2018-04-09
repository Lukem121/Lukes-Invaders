package main;

public class Player extends Actor implements Stage {

	public Player() {
		this.x = width/2;
		this.y = height - 70;
		this.w = 60;
		this.h = 60;
		this.speed = 4;
	}
	
	void update(){
		move();
	}

	void move() {
		
		if (x <= width - w && key.right) 
		{
			x = x + speed;
		}
		if (x >= 0 && key.left)
		{
			x = x - speed;	
		}
	}

}
