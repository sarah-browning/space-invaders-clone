import java.awt.Graphics;

public class Starship extends Sprite {
	
	//Attributes
	protected Boolean dying;
	
	//Getters
	public Boolean getDying() {
		return dying;
	}
	
	//Setters
	public void setDying(Boolean dying) {
		this.dying = dying;
	}
	
	//Constructor
	public Starship() {
		super(70, 60, 450, 600);
		this.dying = false;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		
	}
}