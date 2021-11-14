import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Alien extends Sprite {
	
	//Attributes
	private static final long serialVersionUID = -173465902261771273L;
	private double speed, velocity;
	private int pointValue;
	private Boolean isShooting;
	private BufferedImage alien;

	//Constructors
	public Alien(int x, int y, int width, int height, int pointValue, String filename) {
		super(x, y, width, height);
		this.pointValue = pointValue;
		this.speed = 0.25;
		this.velocity = 1;
		this.isShooting = false;
		ImageLoader loader = new ImageLoader();
		alien = loader.loadImage(filename);
	}
	 
	//Update Method
	public void update() {
		setX((int) (x+ velocity));
	}
	
	//Render Method
	public void render(Graphics g) {
		g.drawImage(alien, this.x, this.y, null);
	}

	//Getters
	public double getSpeed() {
		return speed;
	}

	public double getVelocity() {
		return velocity;
	}

	public int getPointValue() {
		return pointValue;
	}

	public Boolean getIsShooting() {
		return isShooting;
	}

	public BufferedImage getAlien() {
		return alien;
	}
	
	//Setters
	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public void setVelocity(double velocity) {
		this.velocity = velocity;
	}

	public void setPointValue(int pointValue) {
		this.pointValue = pointValue;
	}

	public void setIsShooting(Boolean isShooting) {
		this.isShooting = isShooting;
	}

	public void setAlien(BufferedImage alien) {
		this.alien = alien;
	}
}