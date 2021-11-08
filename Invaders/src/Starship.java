import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Starship extends Sprite {
	
	//Attributes
	protected Boolean dying;
	private BufferedImage starship;
	
	//Getters
	public Boolean getDying() {
		return dying;
	}
	
	//Setters
	public void setDying(Boolean dying) {
		this.dying = dying;
	}
	
	//Constructor
	public Starship(int x, int y, SpaceInvadersPrep game) {
		super(70, 60);
		this.dying = false;
		ImageLoader loader = new ImageLoader();
		starship = loader.loadImage("/images/starship.png");
	}

	public void tick() {
		
	}
	
	public void render(Graphics g) {

		g.drawImage(starship, 400, 550, null);
	}
}