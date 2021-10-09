import java.awt.Container;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class SpaceInvadersPrep extends JFrame {

	private static final long serialVersionUID = 5224759096658742751L;
	
	//Memory Storage
	private Alien[] myAliens;
	private Starship myStarship;
	
	private JLabel StarshipLabel, AlienLabel;
	private ImageIcon StarshipImage, AlienImage;
	
	//Container to hold graphics
	private Container content;
	
	//GUI Setup
	public SpaceInvadersPrep() {
		super("Space Invaders");
		setSize(GameProperties.SCREEN_WIDTH, GameProperties.SCREEN_HEIGHT);		//TODO: Make sure to change to correct size in GameProperties.java
		
		//create new spaceship
		myStarship = new Starship();
		StarshipLabel = new JLabel();
		
		//create new array of aliens
		myAliens = new Alien[55];		//TODO: 
		
		//Exit game on window close
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	//Main
	public static void main( String[] args ) {
		SpaceInvadersPrep myGame = new SpaceInvadersPrep();
		myGame.setVisible(true);
	}
	
}
