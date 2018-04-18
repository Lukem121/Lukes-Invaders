import java.awt.Graphics;


public class Player extends Actor implements Stage {

	
	public int Score;

	//This is the constructor for the player it initialises the variables from the actor class
	public Player() {
		
		this.Width = 60;
		this.Height = 60;
		this.LocationX = Stage.WIDTH / 2 - 30;
		this.LocationY = Stage.HEIGHT -60;
		this.Speed = 5;
		this.Life = 3;
	}

	/**
	 * This method is used to move the player along the x axes 
	 */
	void move(Keyboard key){
		if (LocationX <= Stage.WIDTH - Width && key.right) 
		{
			LocationX = LocationX + Speed;
		}
		if (LocationX >= 0 && key.left)
		{
			LocationX = LocationX - Speed;
		}
				
	}
	
	/**
	 * This method draws the player
	 */
	public void draw(Graphics g){
		g.drawImage(SpriteCache.player, LocationX, LocationY, null);
	}

}
