import java.awt.Graphics;
import java.util.ArrayList;

public class GameController {

	private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	private ArrayList<Alien> aliens = new ArrayList<Alien>();
	
	Bullet TempBullet;
	Alien TempAlien;
	SpaceInvadersPrep game;
	
	public GameController(SpaceInvadersPrep game) {
		this.game = game;
		
		for (int x = 0; x < 600 ; x += 55) {
			addAlien(new Alien(x, 80, 50, 50, "/images/alien1.png"));
//			System.out.println("X is " + x);
			addAlien(new Alien (x, 130, 50, 50, "/images/alien2.png"));
			addAlien(new Alien (x, 180, 50, 50, "/images/alien2.png"));
			addAlien(new Alien (x, 230, 50, 50, "/images/alien3.png"));
			addAlien(new Alien (x, 280, 50, 50, "/images/alien3.png"));
		}
	}
	
	public void tick() {
		for (int i = 0; i < bullets.size(); i++) {
			TempBullet = bullets.get(i);
			
			if (TempBullet.getY() < 30) {
				removeBullet(TempBullet);
			}
			
			TempBullet.tick();
		}
		
		for(int i = 0; i < aliens.size(); i++) {
			TempAlien = aliens.get(i);
			TempAlien.tick();
		}
	}
	
	public void render(Graphics g) {
		for (int i = 0; i < bullets.size(); i++) {
			TempBullet = bullets.get(i);
			TempBullet.render(g);
		}
		
		for (int i = 0; i < aliens.size(); i++) {
			TempAlien = aliens.get(i);
			TempAlien.render(g);
//			System.out.println("Temp Alien #" + i + " x: " + TempAlien.getX() + " y: " + TempAlien.getY());
		}
	}
	
	public void addBullet(Bullet bullet) {
		bullets.add(bullet);
	}
	
	public void removeBullet(Bullet bullet) {
		bullets.remove(bullet);
	}
	
	public void addAlien(Alien alien) {
		aliens.add(alien);
	}
	
	public void removeAlien(Alien alien) {
		aliens.remove(alien);
	}
}
