import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Bullet extends Sprite {
	
	//Attributes
	private BufferedImage bullet;
	
	//Getters
	public BufferedImage getBullet() {
		return bullet;
	}
	
	//Setters
	public void setBullet(BufferedImage bullet) {
		this.bullet = bullet;
	}
	
	//Constructor
	public Bullet(int x, int y, SpaceInvaders game) {
		super(x, y, 55, 51);
		ImageLoader loader = new ImageLoader();
		bullet = loader.loadImage("/res/bullet1.png");
	}

	//Other Functions
	public void update() {
		setY(y - GameProperties.CHAR_STEP);
		
	}
	
	public void render(Graphics g) {
		
		g.drawImage(bullet, x, y, null);
	}
}
