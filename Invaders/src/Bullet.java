import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Bullet extends Sprite {
	
	//Attributes
	private static final long serialVersionUID = 493614412718180249L;
	private BufferedImage bullet;
	
	//Constructor
	public Bullet(int x, int y, SpaceInvaders game) {
		super(x, y, 55, 51);
		ImageLoader loader = new ImageLoader();
		bullet = loader.loadImage("/res/bullet1.png");
	}

	//Update Method
	public void update() {
		setY(y - GameProperties.CHAR_STEP);
		
	}
	
	//Render Method
	public void render(Graphics g) {
		
		g.drawImage(bullet, x, y, null);
	}
	
	//Getters
	public BufferedImage getBullet() {
		return bullet;
	}
	
	//Setters
	public void setBullet(BufferedImage bullet) {
		this.bullet = bullet;
	}
}
