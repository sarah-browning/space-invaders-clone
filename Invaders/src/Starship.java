public class Starship extends Sprite {
	
	protected Boolean shooting, dying;
	
	//getters
	public Boolean getShooting() {
		return shooting;
	}
	public Boolean getDying() {
		return dying;
	}
	
	//Mutators
	public void setShooting(Boolean shooting) {
		this.shooting = shooting;
	}
	public void setDying(Boolean dying) {
		this.dying = dying;
	}
	
	//Constructor
	public Starship() {
		super(70, 60,"images/starship.png");
		this.shooting = false;
		this.dying = false;
	}
	
}