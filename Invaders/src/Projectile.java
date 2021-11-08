import java.awt.Graphics;

public class Projectile extends Sprite {
	
	//Attributes
	protected Boolean moving;
	
	//Getters
	public Boolean getMoving() {
		return moving;
	}
	
	public Boolean getDying() {
		return dying;
	}
	
	//Setters
	public void setMoving(Boolean moving) {
		this.moving = moving;
	}	
	
	public void setDying(Boolean dying) {
		this.dying = dying;
	}
	
	//Constructor
	public Projectile() {
		super(15, 25, 0, 0);
		this.moving = true;
	}

	//Other Functions
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		
	}
}
