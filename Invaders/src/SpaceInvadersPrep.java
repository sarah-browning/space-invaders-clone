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
	private Alien myAliens;
	private Starship myStarship;
	
	//Labels to Display Graphics
	private JLabel AlienLabel, BackgroundLabel, StarshipLabel;
	
	//Container to hold graphics
	private Container content;
	
	//GUI Setup
	public SpaceInvadersPrep() {
		super("Space Invaders");
		setSize(GameProperties.SCREEN_WIDTH, GameProperties.SCREEN_HEIGHT);
		setResizable(false);
		
		content = getContentPane();
		content.setBackground(Color.black);		
		//Set layout manager to null to allow absolute positioning
		setLayout(null);
		
		BackgroundLabel = new JLabel( new ImageIcon("./images/background.jpg") );
		BackgroundLabel.setSize(GameProperties.SCREEN_WIDTH, GameProperties.SCREEN_HEIGHT);

//	TODO: Alternate method of drawing images - may work better for Alien display - more research needed
//				https://docs.oracle.com/javase/tutorial/2d/images/index.html
//
//		panel = new JPanel() {
//			
//			protected void paintComponent (Graphics g) {
//				BufferedImage background;
//				try {
//					img = ImageIO.read(new File("images/background.jpg"));
//					g.drawImage(background, 0, 0, 900, 650, null);
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		};		
	
		//Create new player Starship
		myStarship = new Starship();
		myStarship.setX(400);
		myStarship.setY(500);
		StarshipLabel = new JLabel( new ImageIcon( getClass().getResource( myStarship.getFilename() ) ));
		StarshipLabel.setSize( myStarship.getWidth(), myStarship.getHeight() );
		StarshipLabel.setLocation(myStarship.getX(), myStarship.getY());
		
		//Create Alien Array
		myAliens = new Alien();
		myAliens.setX(80);
		myAliens.setY(50);
		AlienLabel = new JLabel( new ImageIcon( getClass().getResource( myAliens.getFilename() ) ));
		AlienLabel.setSize( myAliens.getWidth(), myAliens.getHeight() );
		AlienLabel.setLocation( myAliens.getX(), myAliens.getY() );
		
		//TODO: Create vertical and horizontal array of aliens in Alien.java => 11 per line

		
		//Add components to window
		add(AlienLabel);
		add(StarshipLabel);
		add(BackgroundLabel);
		
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
