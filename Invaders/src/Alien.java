public class Alien extends Sprite {
	
	//attributes
	protected int pointValue;
	protected Boolean moving, dying;
	
	//getters
	public int getPointValue() {
		return pointValue;
	}
	public Boolean getMoving() {
		return moving;
	}
	public Boolean getDying() {
		return dying;
	}
	
	
	//setters
	public void setPointValue(int pointValue) {
		this.pointValue = pointValue;
	}
	
	public void setMoving(Boolean moving) {
		this.moving = moving;
	}
	public void setDying(Boolean dying) {
		this.dying = dying;
	}

	//Constructors
	public Alien() {
		super(80, 50, 50, 50,"images/alien1.png");
		this.pointValue = 50;
		this.moving = true;
		this.dying = false;
	}
	
	//Other functions
	
}