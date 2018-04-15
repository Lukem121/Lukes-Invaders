
public class Bullet {

	int LocationX = 1000;
	int LocationY = 1000;
	int speed;
	boolean shot = false;
	
	public void update(){
		
		if(shot){
			LocationY = LocationY - 10;
		}
		
	}
	
	public int getLocationY(){
		return LocationY;
	}
	public int getLocationX(){
		return LocationX;
	}
	
}
