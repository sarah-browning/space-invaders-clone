import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Alien extends Sprite {
	
	//Attributes
	private int pointValue;
	private Boolean shooting, dying;
	private BufferedImage alien;
	
	//Getters
	public int getPointValue() {
		return pointValue;
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
	
	public void setMoving(Boolean shooting) {
		this.shooting = shooting;
	}	
	
	public void setDying(Boolean dying) {
		this.dying = dying;
	}

	//Constructors
	public Alien(int x, int y, int width, int height, String filename) {
		super(x, y, width, height);
		this.pointValue = 50;
		this.shooting = false;
		this.dying = false;
		ImageLoader loader = new ImageLoader();
		alien = loader.loadImage(filename);
	}
	
	//Other Functions
	public void tick() {
		x += 1;
		
//		if (x >= 790 ) x -= 1;
//		if (x <= 40) x += 1;
		
	}
	
	public void render(Graphics g) {
		g.drawImage(alien, this.x, this.y, null);
	}
}