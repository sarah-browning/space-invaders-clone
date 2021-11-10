import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;

public class GameController {

	//Attributes
	private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	private LinkedList<Alien> aliens = new LinkedList<Alien>();
	private Bullet TempBullet;
	private Alien TempAlien;
	private SpaceInvaders game;
	private int bulletCount = 0;
	
	//Getters
	public SpaceInvaders getGame() {
		return game;
	}
	public int getBulletCount() {
		return bulletCount;
	}
	public ArrayList<Bullet> getBullets() {
		return bullets;
	}
	public LinkedList<Alien> getAliens() {
		return aliens;
	}
	public Bullet getTempBullet() {
		return TempBullet;
	}
	public Alien getTempAlien() {
		return TempAlien;
	}
	
	//Setters
	public void setGame(SpaceInvaders game) {
		this.game = game;
	}
	public void setBulletCount(int bulletCount) {
		this.bulletCount = bulletCount;
	}
	public void setBullets(ArrayList<Bullet> bullets) {
		this.bullets = bullets;
	}
	public void setAliens(LinkedList<Alien> aliens) {
		this.aliens = aliens;
	}
	public void setTempBullet(Bullet tempBullet) {
		TempBullet = tempBullet;
	}
	public void setTempAlien(Alien tempAlien) {
		TempAlien = tempAlien;
	}

	//Constructor
	public GameController(SpaceInvaders game) {
		this.setGame(game);
		
		
		for (int x = 40; x < 600 ; x += 55) {
			addAlien(new Alien(x, 80, 50, 50, 50, "/images/alien1.png"));
			addAlien(new Alien (x, 130, 50, 50, 40, "/images/alien2.png"));
			addAlien(new Alien (x, 180, 50, 50, 40, "/images/alien2.png"));
			addAlien(new Alien (x, 230, 50, 50, 20, "/images/alien3.png"));
			addAlien(new Alien (x, 280, 50, 50, 20, "/images/alien3.png"));
		}
	}
	
	//Other Functions
	//Update Method
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
			if (aliens.getFirst().getX() <= 40) {
				TempAlien.setY(TempAlien.getY() + 10);
				TempAlien.setSpeed(+1);
				//TODO - Figure out how to stop first alien from dropping 10 pixels 
			}
			
			if (aliens.getLast().getX() >= (GameProperties.SCREEN_WIDTH - 90)) {
				TempAlien.setY(TempAlien.getY() + 10);
				TempAlien.setSpeed(-1);
			}
			
			TempAlien.update();
		}
	}
	
	//Render Method
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
	
	//Add Bullet Method
	public void addBullet(Bullet bullet) {
		if (bulletCount <= 1) {
			bullets.add(bullet);
			bulletCount += 1;
		}
	}
	
	//Remove Bullet Method
	public void removeBullet(Bullet bullet) {
		bullets.remove(bullet);
		bulletCount -= 1;
	}
	
	//Add Alien Method
	public void addAlien(Alien alien) {
		aliens.add(alien);
	}
	
	//Remove Alien Method
	public void removeAlien(Alien alien) {
		aliens.remove(alien);
		TempAlien.visible = false;
	}
}
