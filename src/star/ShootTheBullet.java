package star;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class ShootTheBullet extends JFrame {

	public ShootTheBullet() {
		
		initUI();
		
	}
	
	private void initUI() {
		
		add(new CraftBoard());
		
		setSize(500, 500);
		setResizable(false);
		setTitle("Shoot the Bullet");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
				ShootTheBullet stb = new ShootTheBullet();
				stb.setVisible(true);
				
			}
			
		});
		
	}
	
}
