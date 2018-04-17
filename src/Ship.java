import java.awt.Graphics;


public class Ship extends Actor implements Stage {

	public Ship() {
		this.LocationX = Stage.WIDTH - 60;//was -60
		this.LocationY = 40;
	}
	
	void update(){
		//this.LocationX += 2;
		checkCollision();
	}
	
	void draw(Graphics g){
		g.fillRect(this.LocationX, this.LocationY, 50, 50);
		g.drawImage(SpriteCache.alienShip, this.LocationX, LocationY, null);
	}
	
	void checkCollision(){
		if(Stage.b.LocationY > this.LocationY && Stage.b.LocationY < this.LocationY + 60 &&
				Stage.b.LocationX > this.LocationX && Stage.b.LocationX < this.LocationX + 60)
		{
			System.out.println("test");
		}
	}
	
}
