import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SpaceInvadersPrep extends JFrame {

	private static final long serialVersionUID = 5224759096658742751L;
	
	//Memory Storage
	private Alien myAliens;
	private Starship myStarship;
	private int[] aliensX = new int[55];
	private int[] aliensY = new int[55];

	
	private JLabel BackgroundLabel, StarshipLabel, AlienLabel;
	private ImageIcon BackgroundImage, AlienImage, StarshipImage;
	
	//Container to hold graphics
	private Container content;
	
	//GUI Setup
	public SpaceInvadersPrep() {
		super("Space Invaders");
		setSize(GameProperties.SCREEN_WIDTH, GameProperties.SCREEN_HEIGHT);
		setResizable(false);
		
		BackgroundLabel = new JLabel();
		BackgroundImage = new ImageIcon("./background.jpg");
		BackgroundLabel.setIcon(BackgroundImage);
		BackgroundLabel.setSize(GameProperties.SCREEN_WIDTH, GameProperties.SCREEN_HEIGHT);

			//Alternate method of drawing images:
			//		protected void paintComponent (Graphics g) {
			//		BufferedImage background;
			//		try {
			//			background = ImageIO.read(new File("./background.jpg"));
			//			g.drawImage(background, 0, 0, 900, 650, null);
			//			
			//		} catch (IOException e) {
			//			e.printStackTrace();
			//		}
			//	}
			//};
		
		
		add(BackgroundLabel);
		setSize(GameProperties.SCREEN_WIDTH, GameProperties.SCREEN_HEIGHT);
		
		//Create new spaceship
		myStarship = new Starship();
		StarshipLabel = new JLabel();
		
		//Exit game on window close
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	//Main
	public static void main( String[] args ) {
		SpaceInvadersPrep myGame = new SpaceInvadersPrep();
		myGame.setVisible(true);
	}
	
}
