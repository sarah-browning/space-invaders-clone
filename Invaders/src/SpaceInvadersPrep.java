import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class SpaceInvadersPrep extends JFrame implements Runnable {
	
	//Attributes
	private static final long serialVersionUID = 9121643710941167328L;
	private Boolean running = false;
	private Thread t;
	private BufferedImage background;
	private Starship starship;
	
	
	//Prepare GUI
	public SpaceInvadersPrep() {
		super("Space Invaders");
		setSize(GameProperties.SCREEN_WIDTH, GameProperties.SCREEN_HEIGHT);
		setResizable(false);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	//Game Initialization
	public void init() {
		
		//Load Images
		ImageLoader loader = new ImageLoader();
			background = loader.loadImage("/images/starbkg.jpg");	
		starship = new Starship( 70, 60, this);
	}
	
	//Start the thread if not already running
	private synchronized void start() {
		if (running)
			return;
		
		running = true;
		t = new Thread(this);
		t.start();
	}
	
	//Stop the thread from running and exit the game
	private synchronized void stop() {
		if (!running) 
			return;
		
		running = false;
		try {
			t.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(1);
	}
	
	@Override
	//Run the game
	public void run() {
		init();
		long initialTime = System.nanoTime();
		final double numTicks = 60.0;
		double nanoseconds = 1000000000 / numTicks;
		double delta = 0;
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
		//Game Loop - Tracks time and latency to render and update game at a constant 60fps
		while (running) {
			long now = System.nanoTime();
			delta += (now - initialTime) / nanoseconds;
			initialTime = now;
			if ( delta >= 1 ) {
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
			
			if (System.currentTimeMillis() - timer > 1000) {
				timer+= 1000;
				System.out.println(updates + " Ticks, FPS " + frames);
				updates = 0;
				frames = 0;
			}
		}
		stop();
	}
	
	//everything in the game that updates
	private void tick() {
		starship.tick();
	}
	
	//everything in the game that renders
	private void render() {
		//create a strategy for multi-buffering
		BufferStrategy bs = this.getBufferStrategy();
		
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		//draw the buffered graphics
		Graphics g = bs.getDrawGraphics();
		
		g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
		starship.render(g);

		g.dispose();
		bs.show();
	}
	
	//Main
	public static void main( String[] args ) {
		SpaceInvadersPrep game = new SpaceInvadersPrep();
		game.setVisible(true);
		game.start();
	}
	
}
