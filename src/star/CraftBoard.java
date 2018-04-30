package star;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

public class CraftBoard extends JPanel implements ActionListener {

	private Image background;
	private Timer timer;
	private Craft craft;
	private CloudOne cloudone;
	private CloudOne acloudone;
	private CloudTwo cloudtwo;
	private CloudTwo acloudtwo;
	private CloudThree cloudthree;

	private final int DELAY = 10;
	
	private ArrayList<Enemy> aliens;
	private boolean ingame;
	
	private final int [][] pos = {
			
			// enemy positions
	//		{2380, 29}, {2500, 59}, {1380, 89}, {780, 109}, {580, 139},
	//		{680, 239}, {790, 259}, {760, 50}, {790, 150}, {980, 209}, 
	//		{560, 45}, {510, 70}, {930, 159}, {590, 80}, {530, 60}, {940, 59},
			{990, 30}, {920, 200}, {900, 450}, {660, 350}, {540, 290}, 
			{810, 420}, {860, 360}, {740, 180}, {820, 78}, {490, 170}, 
			{700, 130}
			
	};
	
	public CraftBoard() {
		
		initBoard();
		
	}
	
	private void initBoard() {
		
		addKeyListener(new TAdapter());
		setFocusable(true);
		
		// set background image
		ImageIcon ii = new ImageIcon("Sky2.png");
		background = ii.getImage();
		
		ingame = true;
		setDoubleBuffered(true);
		
		// set bunny head position
		craft = new Craft(40, 60);
		
		// set cloud positions
		cloudone = new CloudOne(25, 25);
		acloudone = new CloudOne(0, 300);
		cloudtwo = new CloudTwo(250, 200);
		acloudtwo = new CloudTwo(400, 50);
		cloudthree = new CloudThree(300, 300);
		
		initAliens();
		
		timer = new Timer(DELAY, this);
		timer.start();
		
	}
	
	public void initAliens() {
		
		aliens = new ArrayList<>();
		
		for (int[] p : pos) {
			aliens.add(new Enemy(p[0], p[1]));
		}
		
	}
	
	@Override
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		g.drawImage(background, 0, 0, null);

		// draws objects onto board
		if (ingame) {
			drawBun(g);
			drawAliens(g);
		} else {
			drawGameOver(g);
		}
		
		Toolkit.getDefaultToolkit().sync();
		
	}
	
	public void drawBun(Graphics g) {
		
		Graphics2D g2d = (Graphics2D) g;
		
		// draw images of clouds
		g2d.drawImage(cloudone.getImage(), cloudone.getX(), cloudone.getY(), this);
		g2d.drawImage(acloudone.getImage(), acloudone.getX(), acloudone.getY(), this);
		g2d.drawImage(cloudtwo.getImage(), cloudtwo.getX(), cloudtwo.getY(), this);
		g2d.drawImage(acloudtwo.getImage(), acloudtwo.getX(), acloudtwo.getY(), this);
		g2d.drawImage(cloudthree.getImage(), cloudthree.getX(), cloudthree.getY(), this);
		
		g2d.drawImage(craft.getImage(), craft.getX(), craft.getY(), this);
	
		ArrayList ms = craft.getMissiles();
		
		// draws images of carrots 
		for (Object m1 : ms) {
			
			Carrot m = (Carrot) m1;
			g2d.drawImage(m.getImage(), m.getX() - 10, m.getY() + 7, this);
			
		}
		
		
	}
	
	private void drawAliens(Graphics g) {
		
		// draws images of bananas
		for (Enemy a: aliens) {
			
			if (a.isVisible()) {
				g.drawImage(a.getImage(), a.getX(), a.getY(), this);
			}
			
		}
		
		g.setColor(Color.WHITE);
		g.drawString("Bananas left: " + aliens.size(), 5, 15);
		
	}

	private void drawGameOver(Graphics g) {
		
		String msg = "Game Over";
		
		if (aliens.size() == 0) {
			msg = "You Win!";
		}
		
		Font small = new Font("Helvetica", Font.BOLD, 14);
		FontMetrics fm = getFontMetrics(small);
		
		g.setColor(Color.WHITE);
		g.setFont(small);
		g.drawString(msg, (500 - fm.stringWidth(msg)) / 2, 236);
	
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		inGame();
		
		updateClouds();
		
		updateMissiles();
		
		if (craft.isVisible()) {
			craft.move();
		}
		
		updateAliens();
		checkCollisions();
		
		repaint();
		
	}
	
	private void inGame() {
		
		if (!ingame) {
			timer.stop();
		}
		
	}
	
	private void updateClouds() {
		
		// make clouds move in the background
		if (cloudone.isVisible()) {
			cloudone.move();
		}
		
		if (acloudone.isVisible()) {
			acloudone.move();
		}
		
		if (cloudtwo.isVisible()) {
			cloudtwo.move();
		}
		
		if (acloudtwo.isVisible()) {
			acloudtwo.move();
		}
		
		if (cloudthree.isVisible()) {
			cloudthree.move();
		}
		
	}
	
	private void updateMissiles() {
		
		// moves carrots -->
		ArrayList ms = craft.getMissiles();
		
		for (int i = 0; i < ms.size(); i++) {
			
			Carrot m = (Carrot) ms.get(i);
			
			if (m.isVisible()) {
				m.move();
			} else {
				ms.remove(i);
			}
			
		}
		
	}
	
	private void updateAliens() {
		
		// check if any bananas are left
		if (aliens.isEmpty()) {
			
			ingame = false;
			return;
			
		}
		
		// move bananas <--
		for (int i = 0; i < aliens.size(); i++) {
			
			Enemy a = aliens.get(i);
			if (a.isVisible()) {
				a.move();
			} else {
				aliens.remove(i);
			}
			
		}
		
	}
	
	private void checkCollisions() {
		
		Ellipse2D r3 = craft.getBounds();
		
		// check if bunny head collides with banana
		for (Enemy alien: aliens) {
			
			Ellipse2D r2 = alien.getBounds();
			
			if (r3.intersects(r2.getBounds2D())) {
				craft.setVisible(false);
				alien.setVisible(false);
				ingame = false;
			}
			
		}
		
		ArrayList<Carrot> ms = craft.getMissiles();
		
		// check if carrot collides with banana
		for (Carrot m: ms) {
			
			Ellipse2D r1 = m.getBounds();
			
			for (Enemy alien : aliens) {
				
				Ellipse2D r2 = alien.getBounds();
				
				if (r1.intersects(r2.getBounds2D())) {
					m.setVisible(false);
					alien.setVisible(false);
				}
				
			}
			
		}
		
	}
	
	private class TAdapter extends KeyAdapter {
		
		@Override 
		public void keyReleased(KeyEvent e) {
			craft.keyReleased(e);
		}
		
		@Override
		public void keyPressed(KeyEvent e) {
			craft.keyPressed(e);
		}
		
	}
	
}
