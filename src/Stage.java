import java.util.ArrayList;


public interface Stage {
	
	public static int WIDTH = 700;
	public static int HEIGHT = 650;
	public static int STATE = 0;
	
	ArrayList<Monster> monsterArray = new ArrayList<Monster>();
	public static Player p = new Player();
	public static Ship as = new Ship();
	public static Bullet b = new Bullet();
	public static Sandbag[] sb = new Sandbag[8];
	public static Monster[] m = new Monster[48];
	

}
