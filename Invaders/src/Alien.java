import java.awt.Graphics;

public class Alien extends Sprite {
	
	//Attributes
	private int pointValue;
	private Boolean shooting, dying;
	
	//Getters
	public int getPointValue() {
		return pointValue;
	}

	public Boolean getMoving() {
		return shooting;
	}
	
	public Boolean getDying() {
		return dying;
	}
	
	//Setters
	public void setPointValue(int pointValue) {
		this.pointValue = pointValue;
	}
	
	public void setMoving(Boolean moving) {
		this.shooting = moving;
	}	
	
	public void setDying(Boolean dying) {
		this.dying = dying;
	}

	//Constructors
	public Alien() {
		super(50, 50, 50, 80);
		this.pointValue = 50;
		this.shooting = false;
		this.dying = false;
	}
	
	//Other Functions
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		
	}
}