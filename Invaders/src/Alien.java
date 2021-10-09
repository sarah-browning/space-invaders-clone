
public class Alien extends Sprite {
	//Attributes
	Boolean dying;
		
	//Getters
	public Boolean getDying() { return dying; }
		
	//Setters
	public void setDying(Boolean dying) { this.dying = dying;}
		
	//Constructor
	public Alien() {
		super(67, 59,"placeholder-ship.png");
		this.dying = false;
	//TODO: call constructor from sprite with alien sprite width, height, filename once sprite finalized
	}
}
