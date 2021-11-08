import java.awt.Graphics;
import java.util.ArrayList;

public class GameController {

	private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	
	Bullet TempBullet;
	SpaceInvadersPrep game;
	
	public GameController(SpaceInvadersPrep game) {
		this.game = game;
	}
	
	public void tick() {
		for (int i = 0; i < bullets.size(); i++) {
			TempBullet = bullets.get(i);
			
			if (TempBullet.getY() < 30) {
				removeBullet(TempBullet);
			}
			
			TempBullet.tick();
		}
	}
	
	public void render(Graphics g) {
		for (int i = 0; i < bullets.size(); i++) {
			TempBullet = bullets.get(i);
			TempBullet.render(g);
		}
	}
	
	public void addBullet(Bullet bullet) {
		bullets.add(bullet);
	}
	
	public void removeBullet(Bullet bullet) {
		bullets.remove(bullet);
	}
}
