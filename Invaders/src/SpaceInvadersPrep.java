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
	
	private JLabel AlienLabel, BackgroundLabel, StarshipLabel;
	
	//Container to hold graphics
	private Container content;
	
	//GUI Setup
	public SpaceInvadersPrep() {
		super("Space Invaders");
		setSize(GameProperties.SCREEN_WIDTH, GameProperties.SCREEN_HEIGHT);
		setResizable(false);
		
		BackgroundLabel = new JLabel( new ImageIcon("./images/background.jpg") );
		BackgroundLabel.setSize(GameProperties.SCREEN_WIDTH, GameProperties.SCREEN_HEIGHT);

//	Alternate method of drawing images:
//		panel = new JPanel() {
//			
//			protected void paintComponent (Graphics g) {
//				BufferedImage background;
//				try {
//					img = ImageIO.read(new File("C:\\Image\\Location.jpg"));
//					g.drawImage(background, 0, 0, 900, 650, null);
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		};
		
		//Create new player starship
		myStarship = new Starship();
		StarshipLabel = new JLabel( new ImageIcon( getClass().getResource( myStarship.getFilename() ) ));
		StarshipLabel.setSize( myStarship.getWidth(), myStarship.getHeight() );
		myStarship.setX(450);
		myStarship.setY(600);
//		System.out.println("Starship X: " + myStarship.getX() + " Starship Y: " + myStarship.getY());
		//TODO: Determine why Starship isn't displaying at proper x, y values
		
		//Create Alien Arrays
//		myAliens = new Alien();
//		AlienLabel = new JLabel( new ImageIcon( getClass().getResource( myAliens.getFilename() ) ));
//		AlienLabel.setSize( myAliens.getWidth(), myAliens.getHeight() );
//		myAliens.setX(100);
//		myAliens.setY(80);
		//TODO: Create vertical and horizontal array of aliens in Alien.java => 11 per line
		
		
		content = getContentPane();
		content.setBackground(Color.black);		
		//Set layout manager to null to allow absolute positioning
		content.setLayout(null);
		
		//Add elements to window
//		add(AlienLabel);
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
