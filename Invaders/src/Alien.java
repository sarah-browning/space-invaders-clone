import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Alien extends Sprite {
	
	//Attributes
	private int pointValue;
	protected int speed;
	private Boolean shooting, dying;
	private BufferedImage alien;
	
	//Getters
	public int getPointValue() {
		return pointValue;
	}

	public int getSpeed() {
		return speed;
	}
	public Boolean getMoving() {
		return shooting;
	}
	public Boolean getDying() {
		return dying;
	}
	
	//Setters
	public void setPointValue(int pointValue) {
		this.pointValue = pointValue;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public void setMoving(Boolean shooting) {
		this.shooting = shooting;
	}
	public void setDying(Boolean dying) {
		this.dying = dying;
	}

	//Constructors
	public Alien(int x, int y, int width, int height, int pointValue, String filename) {
		super(x, y, width, height);
		this.pointValue = pointValue;
		this.speed = 1;
		this.shooting = false;
		this.dying = false;
		ImageLoader loader = new ImageLoader();
		alien = loader.loadImage(filename);
	}
	
	//Other Functions
	public void tick() {
		x += speed;
		
		
	}
	
	public void render(Graphics g) {
		g.drawImage(alien, this.x, this.y, null);
	}
}