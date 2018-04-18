import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener, Stage {
	
	//test
	private boolean[] keys;
	public boolean left, right, space, devButtonEnable, devButtonDisable;
	
	public Keyboard() {
		keys = new boolean[256];
	}
	
	public void update(){
		left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
		space = keys[KeyEvent.VK_SPACE];
		devButtonEnable = keys[KeyEvent.VK_EQUALS];
		devButtonDisable = keys[KeyEvent.VK_MINUS];
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
}
