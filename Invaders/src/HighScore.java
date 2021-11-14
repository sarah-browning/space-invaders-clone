import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class HighScore extends JFrame {
	
	//Attributes
	private static final long serialVersionUID = -229626354180127367L;
	private BufferedImage box, title, /*btnMenu,*/ btnQuit;
	private Database db = SpaceInvaders.getDb();
	
	public void render(Graphics g) {
		try {
			ImageLoader loader = new ImageLoader();
			title = loader.loadImage("/res/highscores.png");
			box = loader.loadImage("/res/scorebox.png");
//			btnMenu = loader.loadImage("/res/mainmenu.png");
			btnQuit = loader.loadImage("/res/quitbutton.png");
		
			g.drawImage(title, 175, 60, null);
			g.drawImage(box, 90, 150, null);
//			g.drawImage(btnMenu, 175, 575, null);
//			g.drawImage(btnQuit, 470, 575, null);
			g.drawImage(btnQuit, 322, 575, null);
			
			Font scoreFont = new Font("MV Boli", Font.BOLD, 30);
			g.setFont(scoreFont);
			g.setColor(Color.YELLOW);
			g.drawString("I really wanted to display the high scores ", 120, 300);
			g.drawString("from the database but, I ran out of time", 120, 350);
			g.drawString("to figure it out though they do get stored!", 110, 400);
			
			db.retrieveResults();
			g.dispose();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
