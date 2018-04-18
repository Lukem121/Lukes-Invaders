import java.awt.Graphics;


public class Ship extends Actor implements Stage {
	
	boolean act = false;
	int bulletLocationX;
	int bulletLocationY;
	int bulletSpeed = 5;
	boolean shot = false;

	public Ship() {
		this.LocationX = -60;
		this.LocationY = 40;
		this.Life = 1;
	}
	

	void update(){
		checkCollision();
		act();
	}
	
	void act(){
		this.LocationX += 2;
	}
	
	
	void draw(Graphics g){
		g.drawImage(SpriteCache.alienShip, this.LocationX, LocationY, null);
	}
	
	void checkCollision(){
		if(Stage.b.LocationY > this.LocationY && Stage.b.LocationY < this.LocationY + 60 &&
				Stage.b.LocationX > this.LocationX && Stage.b.LocationX < this.LocationX + 60)
		{
			p.Score += 100;
			this.Life = 0;
			this.LocationY = 5000;
		}
	}
	
}
