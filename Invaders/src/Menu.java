import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Menu extends JFrame{
	
	//Attributes
	private static final long serialVersionUID = 9221558304803846443L;
	private BufferedImage title, btnPlay, btnScore, btnQuit, scoreTable, instructions;
	
	//Render Method
	public void render (Graphics g) {
		try {
			ImageLoader loader = new ImageLoader();
			title = loader.loadImage("/res/title.png");
			btnPlay = loader.loadImage("/res/playbutton.png");
			btnScore = loader.loadImage("/res/scorebutton.png");
			btnQuit = loader.loadImage("/res/quitbutton.png");
			scoreTable = loader.loadImage("/res/scoretable.png");
			instructions = loader.loadImage("/res/instructions.png");
		
			g.drawImage(title, 110, 115, null);
			g.drawImage(scoreTable, 150, 265,null);
			g.drawImage(btnPlay, 500, 250, null);
			g.drawImage(btnScore, 500, 325, null);
			g.drawImage(btnQuit, 500, 400, null);
			g.drawImage(instructions, 107, 500, null);
			
			g.dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
