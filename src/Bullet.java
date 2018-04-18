import java.awt.Graphics;


public class Bullet implements Stage {

	//This is where the variables for the class are defined
	int LocationX = 1000;
	int LocationY = 1000;
	int speed;
	boolean shot = false;
	
	/**
	 * This method is used to updated the class
	 * it controls when the bullet has been shot and how fast it should move
	 */
	public void update(Keyboard key){
		
		if(shot){
			LocationY = LocationY - 10;
		}
		
		if (key.space){
			
			if(shot == false){
				LocationX = p.LocationX + 25;
				LocationY = p.LocationY;
				shot = true;
			}
		}
		
	}
	
	/**
	 * This method draws the bullet
	 */
	public void draw(Graphics g) {
		
		g.drawImage(SpriteCache.bullet, LocationX, LocationY, null);
	}
	
}
