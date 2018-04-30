package star;

public class CloudOne extends Sprite {
	
	public CloudOne (int x, int y) {
		
		super(x, y);
		init();
		
	}
	
	private void init() {
		
		loadImage("Cloud1.png");
		
	}
	
	public void move() {
		
		x -= 1;
		
		if (x < -250) {
			x = 500;
		}
		
	}
	
}
