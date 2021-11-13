import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Starship extends Sprite {
	
	//Attributes
	private static final long serialVersionUID = -2446716139018190733L;
	private int velocity;
	private Boolean isDying, isShooting;
	private BufferedImage starship;
	
	//Constructor
	public Starship(int x, int y, SpaceInvaders game) {
		super(400, 550, 70, 60);
		this.isDying = false;
		this.isShooting = false;
		ImageLoader loader = new ImageLoader();
		starship = loader.loadImage("/res/starship.png");
	}

	//Update Method
	public void update() {
		x += velocity;
		
		//Keep Starship in window bounds
		if (x <= 40) x = 40;
		if (x >= 790 ) x = 790;
	}
	
	//Render Method
	public void render(Graphics g) {

		g.drawImage(starship, this.x, this.y, null);
	}
	
	//Getters
	public int getVelocity() {
		return velocity;
	}
	
	public Boolean getDying() {
		return isDying;
	}
	
	public Boolean getShooting() {
		return isShooting;
	}
	
	//Setters
	public void setVelocity(int velocity) {
		this.velocity = velocity;
	}
	
	public void setDying(Boolean dying) {
		this.isDying = dying;
	}
	
	public void setShooting(Boolean shooting) {
		this.isShooting = shooting;
	}
}