import java.awt.Graphics;

public class Alien extends Sprite {
	
	//Attributes
	protected int pointValue;
	protected Boolean moving, dying;
	
	//Getters
	public int getPointValue() {
		return pointValue;
	}

	public Boolean getMoving() {
		return moving;
	}
	
	public Boolean getDying() {
		return dying;
	}
	
	//Setters
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
		super(50, 50, 50, 80);
		this.pointValue = 50;
		this.moving = true;
		this.dying = false;
	}
	
	//Other Functions
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		
	}
}