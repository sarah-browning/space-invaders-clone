import java.awt.Graphics;
import java.util.LinkedList;

public class GameController {

	//Attributes
	private LinkedList<Bullet> bullets = new LinkedList<Bullet>();
	private LinkedList<Alien> aliens = new LinkedList<Alien>();
//	private LinkedList<Missile> missiles = new LinkedList<Missile>();
	private Bullet TempBullet;
	private Alien TempAlien;
//	private Missile TempMissile;
	private SpaceInvaders game;
	private int bulletCount = 0;
	private int alienCount = 55;
	private int score = 0;
	private Boolean invasionLine = false;
	
	//Constructor
	public GameController(SpaceInvaders game) {
		this.setGame(game);
		
		for (int x = 50; x < 610 ; x += 55) {
			aliens.add(new Alien(x, 80, 50, 50, 50, "/res/alien1.png"));
			aliens.add(new Alien (x, 130, 50, 50, 40, "/res/alien2.png"));
			aliens.add(new Alien (x, 180, 50, 50, 40, "/res/alien2.png"));
			aliens.add(new Alien (x, 230, 50, 50, 20, "/res/alien3.png"));
			aliens.add(new Alien (x, 280, 50, 50, 20, "/res/alien3.png"));
		}
	}
	
	//Update Method
	public void update() {
		moveAliens();
		moveBullets();
//		fireMissiles();
		checkCollision();
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
	
	//Check Collisions
	public void checkCollision() {
	    for(int i = 0; i < bullets.size(); i++){
	        for(int j = 0; j < aliens.size(); j++){
	            Bullet bullet = bullets.get(i);
	            Alien alien = aliens.get(j);
	
	            if(bullet.intersects(alien)){
	            	removeAlien(alien);
	            	removeBullet(bullet);
	                break;
	            }
	        }
	    }
    }
	
	//Move Bullets
	public void moveBullets() {
		for (int i = 0; i < bullets.size(); i++) {
			TempBullet = bullets.get(i);
			
			if (TempBullet.getY() < 30) {
				removeBullet(TempBullet);
			}
			
			TempBullet.update();
		}
	}
	
	//Move Aliens
	public void moveAliens() {
		for(int i = 0; i < aliens.size(); i++) {
			TempAlien = aliens.get(i);
			double speed = TempAlien.getSpeed();
			
			if (aliens.getLast().getX() >= (GameProperties.SCREEN_WIDTH - 90)) {
				TempAlien.setY((int)TempAlien.getY() + 10);
				TempAlien.setSpeed(+speed);
				TempAlien.setVelocity(-speed -1);
			}
			
			if (aliens.getLast().getX() <= 591) {
				TempAlien.setY((int)TempAlien.getY() + 10);
				TempAlien.setSpeed(++speed);
				TempAlien.setVelocity(+speed +1);
			}
			
			if (TempAlien.getY() >= 461) {
				invasionLine = true;
			}				
			TempAlien.update();
		}
	}

//	private void fireMissiles() {
//		
//	}
	
	//Add Bullet Method
	public void addBullet(Bullet bullet) {
		if (bulletCount <= 0) {
			bullets.add(bullet);
			bulletCount = bulletCount + 1;
		}
	}
	
	//Remove Bullet Method
	public void removeBullet(Bullet bullet) {
		bullets.remove(bullet);
		bulletCount = bulletCount - 1;
	}
	
	//Add Alien Method
	public void addAlien(Alien alien) {
		aliens.add(alien);
	}
	
	//Remove Alien Method
	public void removeAlien(Alien alien) {
		alienCount = alienCount -1;
		score = score + TempAlien.getPointValue();
		aliens.remove(alien);
		System.out.println("There are " + alienCount + " aliens left.");
		System.out.println("The score is now " + score + ".");
	}
	
//	//Add Missile Method
//	public void addMissile(Missile missile) {
//		missiles.add(missile);
//	}
	
//	//Remove Missile Method
//	public void removeMissile(Missile missile) {
//		missiles.remove();
//	}

	//Getters
	public LinkedList<Bullet> getBullets() {
		return bullets;
	}

	public LinkedList<Alien> getAliens() {
		return aliens;
	}

//	public LinkedList<Missile> getMissiles() {
//		return missiles;
//	}

	public Bullet getTempBullet() {
		return TempBullet;
	}

	public Alien getTempAlien() {
		return TempAlien;
	}

//	public Missile getTempMissile() {
//		return TempMissile;
//	}

	public SpaceInvaders getGame() {
		return game;
	}

	public int getBulletCount() {
		return bulletCount;
	}

	public int getAlienCount() {
		return alienCount;
	}

	public int getScore() {
		return score;
	}

	public Boolean getInvasionLine() {
		return invasionLine;
	}

	//Setters
	public void setBullets(LinkedList<Bullet> bullets) {
		this.bullets = bullets;
	}

	public void setAliens(LinkedList<Alien> aliens) {
		this.aliens = aliens;
	}

//	public void setMissiles(LinkedList<Missile> missiles) {
//		this.missiles = missiles;
//	}

	public void setTempBullet(Bullet tempBullet) {
		TempBullet = tempBullet;
	}

	public void setTempAlien(Alien tempAlien) {
		TempAlien = tempAlien;
	}

//	public void setTempMissile(Missile tempMissile) {
//		TempMissile = tempMissile;
//	}

	public void setGame(SpaceInvaders game) {
		this.game = game;
	}

	public void setBulletCount(int bulletCount) {
		this.bulletCount = bulletCount;
	}

	public void setAlienCount(int alienCount) {
		this.alienCount = alienCount;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void setInvasionLine(Boolean invasionLine) {
		this.invasionLine = invasionLine;
	}	
}
