package star;

public class Enemy extends Sprite {

	private final int INITIAL_X = 500;
	
	public Enemy(int x, int y) {
		
		super(x, y);
		initAlien();
		
	}
	
	private void initAlien() {
		
		loadImage("Banana.png");
		getImageDimensions();
		
	}
	
	public void move() {
		
		if (x < 0) {
			x = INITIAL_X;
		}
		
		x -= 2;
		
	}
}
