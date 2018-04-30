package star;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class MovingSprite extends JFrame {

	public MovingSprite() {
		
		initUI();
		
	}
	
	private void initUI() {
		
		add(new CraftBoard());
		
		setSize(500, 500);
		setResizable(false);
		setTitle("Moving Sprite");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
		
			@Override
			public void run() {
				MovingSprite ms = new MovingSprite();
				ms.setVisible(true);
				
			}
		});
	}
	
}
