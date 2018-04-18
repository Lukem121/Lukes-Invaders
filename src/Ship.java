import java.awt.Graphics;
import java.util.Random;


public class Ship extends Actor implements Stage {
	
	boolean act = false;
	int bulletLocationX;
	int bulletLocationY;
	int bulletSpeed = 5;
	int  randomNumber;
	int time = 0;
	Random rand = new Random();

	public Ship() {
		this.LocationX = -60;
		this.LocationY = 40;
		this.Life = 1;
	}
	

	void update(){
		checkCollision();
		if(act){
			act();
		}
		
		if(time >= 60){
			WhenToAct();
			time = 0;
		}
		time++;
	}
	
	void act(){
		if(this.LocationX > Stage.WIDTH){
			this.LocationX = - 80;
			act = false;
		}else{
			this.LocationX += 2;
		}
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
	
	public void WhenToAct(){
		randomNumber = rand.nextInt(100) + 1;
		if(randomNumber > 95){
			act = true;
		}
	}
	
}
