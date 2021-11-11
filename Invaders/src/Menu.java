import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

public class Menu {
		
	private Font titleFont;
	private Font btnFont;
	private Rectangle btnPlay;
	private Rectangle btnScore;
	private Rectangle btnQuit;
	
	
	public Menu() {
		 btnPlay = new Rectangle(GameProperties.SCREEN_WIDTH / 2 -125, 300, 250, 50);
		 btnScore = new Rectangle(GameProperties.SCREEN_WIDTH / 2 -125, 400, 250, 50);
		 btnQuit = new Rectangle(GameProperties.SCREEN_WIDTH / 2 -125, 500, 250, 50);
		
		
		try {
			titleFont = Font.createFont(Font.TRUETYPE_FONT, new File("MassEffectGame-123-400.ttf")).deriveFont(50f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("MassEffectGame-123-400.ttf")));
			
		} catch(IOException | FontFormatException e) {
			
		}
		
		try {
			btnFont = Font.createFont(Font.TRUETYPE_FONT, new File("MassEffectGame-123-400.ttf")).deriveFont(20f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("MassEffectGame-123-400.ttf")));
			
		} catch(IOException | FontFormatException e) {
			
		}
	}

	public void render (Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		g.setFont(titleFont);
		g.setColor(Color.GREEN);
		g.drawString("Invaders From", 180, 150);
		g.drawString("Space", 330, 225);
		
		g.setFont(btnFont);
		g.drawString("Play Game", btnPlay.x +50, btnPlay.y +30);
		g2d.draw(btnPlay);
		g.drawString("High Scores", btnScore.x +40, btnScore.y +30);
		g2d.draw(btnScore);
		g.drawString("Quit Game", btnQuit.x +50, btnQuit.y +30);
		g2d.draw(btnQuit);
	}

}
