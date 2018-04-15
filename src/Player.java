
public class Player extends Actor {
	
	//test
	public Player() {
		// TODO Auto-generated constructor stub
	}
	
	public Player(int w, int h, int x, int y, int s) {
		
		this.Width = w;
		this.Height = h;
		this.LocationX = x;
		this.LocationY = y;
		this.Speed = s;
		
		Life = 3;
	}
	
	public void update(){
		
	}

	public int getLocationX() {
		// TODO Auto-generated method stub
		return LocationX;
	}

	public int getLocationY() {
		// TODO Auto-generated method stub
		return LocationY;
	}
	
	public void moveLeft(){
		LocationX = LocationX - Speed;
	}
	public void moveRight(){
		LocationX = LocationX + Speed;
	}

}
