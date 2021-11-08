import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Bullet extends Sprite {
	
	//Attributes
	private Boolean moving;
	BufferedImage bullet;
	
	//Getters
	public Boolean getMoving() {
		return moving;
	}
	
	//Setters
	public void setMoving(Boolean moving) {
		this.moving = moving;
	}
	
	//Constructor
	public Bullet(int x, int y, SpaceInvadersPrep game) {
		super(x, y, 65, 60);
		this.moving = true;
		ImageLoader loader = new ImageLoader();
		bullet = loader.loadImage("/images/bullet.png");
	}

	//Other Functions
	public void tick() {
		y -= 10;
	}
	
	public void render(Graphics g) {
		g.drawImage(bullet, x, y, null);
	}
}
