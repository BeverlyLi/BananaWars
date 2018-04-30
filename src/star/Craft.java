package star;


import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Craft extends Sprite {

	private int dx, dy;
	private ArrayList<Carrot> missiles;
	
	public Craft(int x, int y) {
		
		super(x, y);
		initCraft();
		
	}
	
	private void initCraft() {
		
		missiles = new ArrayList<>();
		loadImage("ClosedBunHead.png");
		getImageDimensions();
		
	}
	
	public void move() {
		
		// move bunny head
		x += dx;
		y += dy;
		
		// make sure bunny head stays in window
		if (x < 1) {
			x = 1;
		}
		
		if (x > 450) {
			x = 450;
		}
		
		if (y < 1) {
			y = 1;
		}
		
		if (y > 420) {
			y = 420;
		}
		
	}
	
	public ArrayList getMissiles() {
		
		return missiles;
		
	}
	
	public void keyPressed(KeyEvent e) {
		
		int key = e.getKeyCode();
	
		// keys that make bunny head do something
		if (key == KeyEvent.VK_SPACE) {
			loadImage("BunHead.png");
			fire();
		}
		
		if (key == KeyEvent.VK_LEFT) {
			dx = -2;
		}
		
		if (key == KeyEvent.VK_RIGHT) {
			dx = 2;
		}
		
		if (key == KeyEvent.VK_UP) {
			dy = -2;
		}
		
		if (key == KeyEvent.VK_DOWN) {
			dy = 2;
		}
		
	}
	
	public void fire() {
		missiles.add(new Carrot(x + width, y + height / 2));
	}
	
	public void keyReleased(KeyEvent e) {
		
		int key = e.getKeyCode();
	
		if (key == KeyEvent.VK_SPACE) {
			loadImage("ClosedBunHead.png");
		}
		
		if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT) {
			dx = 0;
		}
		
		if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) {
			dy = 0;
		}
		
	}
	
}
