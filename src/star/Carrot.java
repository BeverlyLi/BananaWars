package star;

public class Carrot extends Sprite {

	private final int BOARD_WIDTH = 500;
	private final int MISSILE_SPEED = 2;
	
	public Carrot(int x, int y) {
		
		super(x, y);
		
		initCarrot();
		
	}
	
	private void initCarrot() {
		
		loadImage("Carrot.png");
		getImageDimensions();
		
	}
	
	public void move() {
		
		x += MISSILE_SPEED;
		
		if (x > BOARD_WIDTH) {
			vis = false;
		}
		
	}
	
}
