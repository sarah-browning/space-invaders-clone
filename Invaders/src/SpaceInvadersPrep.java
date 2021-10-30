import java.awt.Color;
import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class SpaceInvadersPrep extends JFrame implements KeyListener {

	private static final long serialVersionUID = 6890192808572845938L;
	
	//Memory Storage
	private Alien[] myAliens;
	private Starship myStarship;
	
	//Labels to Display Graphics
	private JLabel[] AlienLabels;
	private JLabel BackgroundLabel, StarshipLabel;
	
	//Container to hold graphics
	private Container content;
	
	//GUI Setup
	public SpaceInvadersPrep() {
		super("Space Invaders");
		setSize(GameProperties.SCREEN_WIDTH, GameProperties.SCREEN_HEIGHT);
		setResizable(false);
		
		content = getContentPane();
		content.setBackground(Color.black);
		setLayout(null);
		
		BackgroundLabel = new JLabel( new ImageIcon("./images/background.jpg") );
		BackgroundLabel.setSize(GameProperties.SCREEN_WIDTH, GameProperties.SCREEN_HEIGHT);		
	
		//Create new Player Starship
		myStarship = new Starship();
		myStarship.setX(400);
		myStarship.setY(500);
		StarshipLabel = new JLabel( new ImageIcon( getClass().getResource( myStarship.getFilename() ) ));
		StarshipLabel.setSize( myStarship.getWidth(), myStarship.getHeight() );
		StarshipLabel.setLocation(myStarship.getX(), myStarship.getY());
		
		
		//Create new array of Aliens
		myAliens = new Alien[33];
		AlienLabels = new JLabel[33];
		
		for (int i = 0; i < myAliens.length; i++) {
				
			myAliens[i] = new Alien();
			myAliens[i].setX(80);
			myAliens[i].setY(50);
			if ( i >= 1 ) {
				myAliens[i].setX(myAliens[i-1].getX() + 55);
			}
			
			
			AlienLabels[i] = new JLabel( new ImageIcon( getClass().getResource( myAliens[i].getFilename() ) ));
			AlienLabels[i].setSize( myAliens[i].getWidth(), myAliens[i].getHeight() );
			AlienLabels[i].setLocation(myAliens[i].getX(), myAliens[i].getY());
			add(AlienLabels[i]);
			System.out.println("X is " + myAliens[i].getX() + " and Y is " + myAliens[i].getY());
		}

		//Add components to window
		add(StarshipLabel);
		
		add(BackgroundLabel);
		content.addKeyListener(this);
		content.setFocusable(true);
		
		//Exit game on window close
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
	//Main
	public static void main( String[] args ) {
		SpaceInvadersPrep myGame = new SpaceInvadersPrep();
		myGame.setVisible(true);
	}

	//Key Listeners
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int sx = myStarship.getX();
		int sy = myStarship.getY();
		
		//move left
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			sx -= GameProperties.CHARACTER_STEP;
			if (sx <= 20) sx = 20;
		//move right
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			sx += GameProperties.CHARACTER_STEP;
			if (sx >= 795) sx = 795;
		}
		
		//set Starship X,Y
		myStarship.setX(sx);
		myStarship.setY(sy);
		//set Starship Label location
		StarshipLabel.setLocation(myStarship.getX(), myStarship.getY());
		myStarship.Display();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
