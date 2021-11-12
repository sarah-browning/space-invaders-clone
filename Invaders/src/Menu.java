import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Menu extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 9221558304803846443L;
	
	private BufferedImage title, btnPlay, btnScore, btnQuit, scoreTable;
	
	public void render (Graphics g) {
		
		ImageLoader loader = new ImageLoader();
		title = loader.loadImage("/res/title.png");
		btnPlay = loader.loadImage("/res/playbutton.png");
		btnScore = loader.loadImage("/res/scorebutton.png");
		btnQuit = loader.loadImage("/res/quitbutton.png");
		scoreTable = loader.loadImage("/res/scoretable.png");
		
		g.drawImage(title, 110, 125, null);
		g.drawImage(scoreTable, 150, 315,null);
		g.drawImage(btnPlay, 500, 300, null);
		g.drawImage(btnScore, 500, 375, null);
		g.drawImage(btnQuit, 500, 450, null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
