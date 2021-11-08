import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Starship extends Sprite {
	
	//Attributes
	private int speed = 0;
	private Boolean dying, shooting;
	private BufferedImage starship;
	
	//Getters
	public int getSpeed() {
		return speed;
	}
	public Boolean getDying() {
		return dying;
	}
	public Boolean getShooting() {
		return shooting;
	}
	
	//Setters
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public void setDying(Boolean dying) {
		this.dying = dying;
	}
	public void setShooting(Boolean shooting) {
		this.shooting = shooting;
	}
	
	//Constructor
	public Starship(int x, int y, SpaceInvadersPrep game) {
		super(400, 550, 70, 60);
		this.dying = false;
		this.shooting = false;
		ImageLoader loader = new ImageLoader();
		starship = loader.loadImage("/images/starship.png");
	}

	public void tick() {
		x += speed;
		
		//keep starship in bounds
		if (x <= 40) x = 40;
		if (x >= 790 ) x = 790;
	}
	
	public void render(Graphics g) {

		g.drawImage(starship, this.x, this.y, null);
	}
}