import java.awt.Graphics;
import java.util.Random;

public class Monster extends Actor implements Stage {

	//This is where the variables are defined for the class	
	int bulletLocationX;
	int bulletLocationY;
	int bulletSpeed = 5;
	int direction = -1;
	int shouldMove = 0;
	int movedLeftAmmount = 4;
	int movedRightAmmount = 0;
	int moveAmmount = 10;
	int moveDownAmmount = 20;
	int  randomNumber;
	int num = 0;
	boolean canDrop = true;
	boolean dropped = false;
	Random rand = new Random();
	Player p = Stage.p;
	Bullet b = Stage.b;
	Sandbag[] sb = GameEngine.sb;
	
	//this is the constructor for the class
	public Monster(int x, int y) {
		this.LocationX = x;
		this.LocationY = y;
		
		bulletLocationX = -500;
		bulletLocationY = -500;
		
	}
	
	/**
	 * This method updates the class it calls other methods within the class
	 */
	public void update(){
		checkCollision();
		reachedBottom();
		dance();
		
		//This code checks if the alien can drop a bomb on the player
		if(canDrop && num > 60){
			drop();
			num = 0;
		}
		num++;
		if(bulletLocationY > Stage.HEIGHT){
			canDrop = true;
		}
		
		if(dropped){
			bulletLocationY = bulletLocationY + bulletSpeed;
		}
		
	}
	
	/**
	 * This method is used to make the aliens move around the screen
	 */
	public void dance(){
			
		shouldMove++;
		if(shouldMove > 45){
			
			shouldMove = 0;
			
			if(direction == -1){
				LocationX = LocationX - moveAmmount;
				movedLeftAmmount++;
			}
			if(direction == 1){
				LocationX = LocationX + moveAmmount;
				movedRightAmmount++;
			}
		}
		
		if(movedLeftAmmount >=9){
			movedLeftAmmount = 0;
			LocationY = LocationY + moveDownAmmount;
			direction = 1;
		}
		if(movedRightAmmount >=9){
			movedRightAmmount = 0;
			LocationY = LocationY + moveDownAmmount;
			direction = -1;
		}
	}
	
	/**
	 * This method is responsible for the "shooting"
	 * uses random number to decide when to "shoot"
	 */
	public void drop(){
		randomNumber = rand.nextInt(100) + 1;
		if(randomNumber > 97){
			canDrop = false;
			dropped = true;
			bulletLocationX = LocationX + 25;
			bulletLocationY = LocationY + 15;
		}
	}
	
	/**
	 * This method checks if the aliens have reached the bottom of the screen
	 */
	void reachedBottom(){
		
		if(this.LocationY + 60 >= p.LocationY){
			GameEngine.gameOver = true;
		}
		 
	}
	
	/**
	 * This method is used to check if the aliens drop has hit the player or the sandbag
	 */
	public void checkCollision(){
				
		
		if(b.LocationY > this.LocationY && b.LocationY < this.LocationY + 45 &&
				b.LocationX > this.LocationX && b.LocationX < this.LocationX + 45)
		{
			Life = 0;
			p.Score += 10;
			LocationX = 5000;
			b.LocationX = 4000;
			b.shot = false;
			monsterArray.remove(1);
		}
		
		if(bulletLocationX > p.LocationX && bulletLocationX < p.LocationX + 60 &&
			bulletLocationY > p.LocationY && bulletLocationY < p.LocationY + 60	)
		{
			bulletLocationX = 5000;
			p.Life--;
		}
		
	}

	/**
	 * This method draws the alien and the alien's bullet
	 */
	public void draw(Graphics g) {

		g.drawImage(SpriteCache.alien, this.LocationX, this.LocationY, null);
		g.drawImage(SpriteCache.alienBullet, this.bulletLocationX, this.bulletLocationY, null);
		
	}
}
