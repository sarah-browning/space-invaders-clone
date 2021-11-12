import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Starship extends Sprite {
	
	//Attributes
	private int speed = 0;
	private Boolean isDying, isShooting;
	private BufferedImage starship;
	
	//Getters
	public int getSpeed() {
		return speed;
	}
	public Boolean getDying() {
		return isDying;
	}
	public Boolean getShooting() {
		return isShooting;
	}
	
	//Setters
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public void setDying(Boolean dying) {
		this.isDying = dying;
	}
	public void setShooting(Boolean shooting) {
		this.isShooting = shooting;
	}
	
	//Constructor
	public Starship(int x, int y, SpaceInvaders game) {
		super(400, 550, 70, 60);
		this.isDying = false;
		this.isShooting = false;
		ImageLoader loader = new ImageLoader();
		starship = loader.loadImage("/res/starship.png");
	}

	public void update() {
		x += speed;
		
		//Keep Starship in window bounds
		if (x <= 40) x = 40;
		if (x >= 790 ) x = 790;
	}
	
	public void render(Graphics g) {

		g.drawImage(starship, this.x, this.y, null);
	}
}