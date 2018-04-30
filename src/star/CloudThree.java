package star;

public class CloudThree extends Sprite {

	public CloudThree(int x, int y) {
		
		super(x, y);
		init();
		
	}
		
	private void init() {
		loadImage("Cloud3.png");
	}
	
	public void move() {
		
		x -= 1;
		
		if (x < -150) {
			x = 500;
		}
		
	}
	
}
