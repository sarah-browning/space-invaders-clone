import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;

public class GameController {

	//Attributes
	private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	private LinkedList<Alien> aliens = new LinkedList<Alien>();
	private Bullet TempBullet;
	private Alien TempAlien;
	private SpaceInvadersPrep game;
	
	//Constructor
	public GameController(SpaceInvadersPrep game) {
		this.game = game;
		
		for (int x = 40; x < 600 ; x += 55) {
			addAlien(new Alien(x, 80, 50, 50, 50, "/images/alien1.png"));
			addAlien(new Alien (x, 130, 50, 50, 40, "/images/alien2.png"));
			addAlien(new Alien (x, 180, 50, 50, 40, "/images/alien2.png"));
			addAlien(new Alien (x, 230, 50, 50, 20, "/images/alien3.png"));
			addAlien(new Alien (x, 280, 50, 50, 20, "/images/alien3.png"));
		}
	}
	
	//Other Functions	
	public void update() {
		for (int i = 0; i < bullets.size(); i++) {
			TempBullet = bullets.get(i);
			
			if (TempBullet.getY() < 30) {
				removeBullet(TempBullet);
			}
			
			TempBullet.update();
		}
		
		for(int i = 0; i < aliens.size(); i++) {
			TempAlien = aliens.get(i);
			
			if (aliens.getLast().getX() >= (GameProperties.SCREEN_WIDTH - 100)) {
				TempAlien.setSpeed(-1);
			}

			if (aliens.getFirst().getX() <= 50) {
				TempAlien.setSpeed(+1);
			}
			
			TempAlien.update();
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
