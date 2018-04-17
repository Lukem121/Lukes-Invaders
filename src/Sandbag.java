import java.awt.Graphics;


public class Sandbag implements Stage{

	int Width;
	int Height;
	int LocationX;
	int LocationY;
	int Speed;
	int state = 0;
	
	int hitAmmount = 0;
	
	//test
	
	Bullet b = GameEngine.b;
	Monster[] m = GameEngine.m;
	
	public Sandbag(int x, int y) {
		
		this.LocationX = x;
		this.LocationY = y;
	}
	
	public void update(){
		checkCollision();
	}
	
	
	public void checkCollision(){
		
		for (int i = 0; i < m.length; i++) {
			if (m[i].bulletLocationX > LocationX && m[i].bulletLocationX < LocationX + 60 
					&& m[i].bulletLocationY > LocationY + 20) 
			{
				m[i].bulletLocationX = 5000;
				hitAmmount++;
				if(hitAmmount >= 2){
					
					hitAmmount = 0;
					if(!(state>=4)){
						state++;
					}else{
						LocationX = 5000;
					}
				}
				
			}
			if(b.LocationX > LocationX && b.LocationX< LocationX + 60
					&& b.LocationY < LocationY + 60 )
			{
				b.LocationX = 5000;
				b.shot = false;
				if(!(state>=4)){
					state++;
				}else{
					LocationX = 5000;
				}
			}
		}
		
	}

	public void draw(Graphics g) {
		g.drawImage(SpriteCache.sandbag[this.state], this.LocationX, this.LocationY, null);
	}
	
}
