package main;

public class Bullet extends Actor implements Stage {
	//test
	
	public Bullet() {
		this.w = 6;
		this.h = 13;
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
