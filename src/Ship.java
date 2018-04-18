import java.awt.Graphics;
import java.util.Random;


public class Ship extends Actor implements Stage {
	
	//This is where the variables for the class are defined
	boolean act = false;
	int bulletLocationX;
	int bulletLocationY;
	int bulletSpeed = 5;
	int  randomNumber;
	int time = 0;
	Random rand = new Random();

	//This is the constructor for the ship class
	public Ship() {
		this.LocationX = -60;
		this.LocationY = 40;
		this.Life = 1;
	}
	
	/**
	 * This method updates the ship class
	 */
	void update(){
		//This code checks if the ship has been hit by a bullet
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
	
	/**
	 * This method makes the ship move across the screen
	 */
	void act(){
		if(this.LocationX > Stage.WIDTH){
			this.LocationX = - 80;
			act = false;
		}else{
			this.LocationX += 2;
		}
	}
	
	
	/**
	 * This method draws the ship
	 */
	void draw(Graphics g){
		g.drawImage(SpriteCache.alienShip, this.LocationX, LocationY, null);
	}
	
	/**
	 * This method checks if the bullet intersecs with the ship
	 */
	void checkCollision(){
		if(Stage.b.LocationY > this.LocationY && Stage.b.LocationY < this.LocationY + 60 &&
				Stage.b.LocationX > this.LocationX && Stage.b.LocationX < this.LocationX + 60)
		{
			p.Score += 100;
			this.Life = 0;
			this.LocationY = 5000;
		}
	}
	
	/**
	 * This method is used to make the ship appear at random times in the game
	 */
	public void WhenToAct(){
		randomNumber = rand.nextInt(100) + 1;
		if(randomNumber > 95){
			act = true;
		}
	}
	
}
