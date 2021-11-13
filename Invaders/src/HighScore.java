import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;

public class HighScore extends JFrame {
	
	//Attributes
	private static final long serialVersionUID = -229626354180127367L;
	private BufferedImage box, title, btnPlay, btnQuit;
	
	public void render(Graphics g) {
		
		ImageLoader loader = new ImageLoader();
		title = loader.loadImage("/res/highscores.png");
		box = loader.loadImage("/res/scorebox.png");
		btnPlay = loader.loadImage("/res/playbutton.png");
		btnQuit = loader.loadImage("/res/quitbutton.png");
		
		g.drawImage(title, 175, 60, null);
		g.drawImage(box, 90, 150, null);
		g.drawImage(btnPlay, 175, 575, null);
		g.drawImage(btnQuit, 470, 575, null);
		
//		Font scoreFont = new Font("MV Boli", Font.BOLD, 50);
//		g.setFont(scoreFont);
//		g.setColor(Color.blue);
//		g.drawString("High Scores", 200, 300);
		
	}
}
