public class Starship extends Sprite {
	
	//Attributes
	Boolean dying;
	
	//Getters
	public Boolean getDying() { return dying; }
	
	//Setters
	public void setDying(Boolean dying) { this.dying = dying;}
	
	//Constructor
	public Starship() {
		super(67, 59,"placeholder-ship.png");
		this.dying = false;
		//TODO: Add proper height,width, filename
	}
	
}
