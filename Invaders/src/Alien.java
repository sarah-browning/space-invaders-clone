import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Alien extends Sprite {
	
	//Attributes
	private int velocity, pointValue;
	private Boolean isShooting, isDying;
	private BufferedImage alien;
	
	//Getters
	public int getPointValue() {
		return pointValue;
	}
	public int getVelocity() {
		return velocity;
	}
	public Boolean getShooting() {
		return isShooting;
	}
	public Boolean getDying() {
		return isDying;
	}
	
	//Setters
	public void setPointValue(int pointValue) {
		this.pointValue = pointValue;
	}
	public void setVelocity(int velocity) {
		this.velocity = velocity;
	}
	public void setShooting(Boolean shooting) {
		this.isShooting = shooting;
	}
	public void setDying(Boolean dying) {
		this.isDying = dying;
	}

	//Constructors
	public Alien(int x, int y, int width, int height, int pointValue, String filename) {
		super(x, y, width, height);
		this.pointValue = pointValue;
		this.velocity = 1;
		this.isShooting = false;
		this.isDying = false;
		ImageLoader loader = new ImageLoader();
		alien = loader.loadImage(filename);
	}
	 
	//Other Functions
	public void update() {
		setX(x+ velocity);
	}
	
	public void render(Graphics g) {
		g.drawImage(alien, this.x, this.y, null);
	}
	
	//Missile subclass
	
}