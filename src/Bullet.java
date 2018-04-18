import java.awt.Graphics;


public class Bullet implements Stage {

	//test
	int LocationX = 1000;
	int LocationY = 1000;
	int speed;
	boolean shot = false;
	
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
	
	public void draw(Graphics g) {
		
		g.drawImage(SpriteCache.bullet, LocationX, LocationY, null);
	}
	
}
