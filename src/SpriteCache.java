import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;


public class SpriteCache {

	private BufferedImage sheet;
	
	public SpriteCache(BufferedImage sheet) 
	{
		this.sheet = sheet;
	}
	
	public BufferedImage crop(int x, int y, int width, int height){
		
		return sheet.getSubimage(x, y, width, height);
		
	}
	
	
	
	public static BufferedImage loadImage(String path){
		try {
			return ImageIO.read(SpriteCache.class.getResource(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}
	
	
	
	private static final int width = 60, height = 60;
	
	public static BufferedImage alien, alienBullet, player, bullet, endGame;
	public static BufferedImage[] sandbag = new BufferedImage[5];
	
	public static void init(){
		SpriteCache sheet = new SpriteCache(SpriteCache.loadImage("/textures/spriteSheet.png"));
		
		alien = sheet.crop(0, 0, width, height);
		alienBullet = sheet.crop(width, height * 3, width, height);
		player = sheet.crop(0, height, width, height);
		bullet = sheet.crop(0, height * 3, width, height);
		endGame = sheet.crop(0, height * 4, 240, height);
		
		int bagNumber = 0;
		
		for (int i = 0; i < 5; i++) {
			sandbag[i] = sheet.crop(bagNumber, height * 2, width, height);
			bagNumber = bagNumber + 60;
			
		}
	}
	
	

	
}
