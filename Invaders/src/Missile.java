import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Missile extends Sprite {
	
	//Attributes
	private static final long serialVersionUID = 8153295757375155548L;
	private BufferedImage missile;
	
	//Constructor
	public Missile(int x, int y, SpaceInvaders game) {
		super(x, y, 55, 51);
		ImageLoader loader = new ImageLoader();
		missile = loader.loadImage("/res/bullet2.png");
	}

	//Update Method
	public void update() {
		setY(y + GameProperties.CHAR_STEP);
		
	}
	
	//Render Method
	public void render(Graphics g) {
		
		g.drawImage(missile, x, y, null);
	}

	//Getters
	public BufferedImage getMissile() {
		return missile;
	}

	//Setters
	public void setMissile(BufferedImage missile) {
		this.missile = missile;
	}
}
