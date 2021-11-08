public class Projectile extends Sprite {

	//additional attributes
	protected Boolean moving;
	
	//accessors
	public Boolean getMoving() {
		return moving;
	}
	
	//mutators
	public void setMoving(Boolean moving) {
		this.moving = moving;
	}
	
	//constructor
	public Projectile() {
		super(15, 25, "images/projectile1.jpg");
		this.moving = true;
	}

}
