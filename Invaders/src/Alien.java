import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Alien extends Sprite {
	
	//Attributes
	private static final long serialVersionUID = -173465902261771273L;
	private int speed, velocity, pointValue;
	private Boolean isShooting;
	private BufferedImage alien;

	//Constructors
	public Alien(int x, int y, int width, int height, int pointValue, String filename) {
		super(x, y, width, height);
		this.pointValue = pointValue;
		this.velocity = 1;
		this.isShooting = false;
		ImageLoader loader = new ImageLoader();
		alien = loader.loadImage(filename);
	}
	 
	//Update Method
	public void update() {
		setX(x+ velocity);
	}
	
	//Render Method
	public void render(Graphics g) {
		g.drawImage(alien, this.x, this.y, null);
	}
	
	//Getters
	public int getPointValue() {
		return pointValue;
	}
	
	public int getVelocity() {
		return velocity;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public Boolean getShooting() {
		return isShooting;
	}
	
	//Setters
	public void setPointValue(int pointValue) {
		this.pointValue = pointValue;
	}
	
	public void setVelocity(int velocity) {
		this.velocity = velocity;		
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public void setShooting(Boolean shooting) {
		this.isShooting = shooting;
	}
}