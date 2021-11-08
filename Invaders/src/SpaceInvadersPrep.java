import javax.swing.JFrame;

public class SpaceInvadersPrep extends JFrame implements Runnable {
	
	//Attributes
	private static final long serialVersionUID = 9121643710941167328L;
	
	//Prepare GUI
	public SpaceInvadersPrep() {
		super("Space Invaders");
		setSize(GameProperties.SCREEN_WIDTH, GameProperties.SCREEN_HEIGHT);
		setResizable(false);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	
	@Override
	public void run() {
		
	}
	
	//Main
	public static void main( String[] args ) {
		SpaceInvadersPrep game = new SpaceInvadersPrep();
		game.setVisible(true);
	}
	
}
