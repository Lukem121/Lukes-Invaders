import java.awt.Graphics;


public class Player extends Actor implements Stage {

	
	public int Score;

	public Player() {
		
		this.Width = 60;
		this.Height = 60;
		this.LocationX = Stage.WIDTH / 2 - 30;
		this.LocationY = Stage.HEIGHT -60;
		this.Speed = 5;
		this.Life = 3;
	}
	
	public void update(){
		
	}
	
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
	
	public void draw(Graphics g){
		g.drawImage(SpriteCache.player, LocationX, LocationY, null);
	}

}
