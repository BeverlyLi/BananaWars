package star;

public class CloudTwo extends Sprite {
	
	public CloudTwo(int x, int y) {
		
		super(x, y);
		init();
		
	}
		
	private void init() {
		loadImage("Cloud2.png");
	}
	
	public void move() {
		
		x -= 1;
		
		if (x < -25) {
			x = 500;
		}
		
	}
	
}
