public class Alien extends Sprite {
	
	//declarations
	protected int pointValue;
	
	//getters
	public int getPointValue() {
		return pointValue;
	}
	
	//setters
	public void setPointValue(int pointValue) {
		this.pointValue = pointValue;
	}

	//Constructors
	public Alien() {
		super(50, 50,"images/alien1.png");
		this.pointValue = 20;
	}
	
}