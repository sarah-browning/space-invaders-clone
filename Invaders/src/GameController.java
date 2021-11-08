import java.awt.Graphics;
import java.util.ArrayList;

public class GameController {

	private ArrayList<Bullet> b = new ArrayList<Bullet>();
	
	Bullet TempBullet;
	SpaceInvadersPrep game;
	
	public GameController(SpaceInvadersPrep game) {
		this.game = game;
	}
	
	public void tick() {
		for (int i = 0; i < b.size(); i++) {
			TempBullet = b.get(i);
			
			if (TempBullet.getY() < 30) {
				removeBullet(TempBullet);
			}
			
			TempBullet.tick();
		}
	}
	
	public void render(Graphics g) {
		for (int i = 0; i < b.size(); i++) {
			TempBullet = b.get(i);
			
			TempBullet.render(g);
		}
	}
	
	public void addBullet(Bullet bullet) {
		b.add(bullet);
		
	}
	
	public void removeBullet(Bullet bullet) {
		b.remove(bullet);
	}
}
