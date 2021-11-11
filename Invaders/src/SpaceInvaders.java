import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class SpaceInvaders extends JFrame implements KeyListener, Runnable {
	
	//Attributes
	private static final long serialVersionUID = 9121643710941167328L;
	private Boolean running = false;
	private Thread t;
	private BufferedImage background;
	private Starship starship;
	private GameController controller;
//	private Menu menu;
	
//	public static enum STATE {
//		MENU,
//		GAME
//	};
//	
//	public static STATE state = STATE.MENU;
	
	//Prepare GUI
	public SpaceInvaders() {
		super("Space Invaders");
		setSize(GameProperties.SCREEN_WIDTH, GameProperties.SCREEN_HEIGHT);
		setResizable(false);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	//Game Initialization
	public void init() {
		requestFocus();
		
		//Load Background
		ImageLoader loader = new ImageLoader();
		background = loader.loadImage("/images/starbkg.jpg");
		
		//Load Components
		addKeyListener(this);
		starship = new Starship( 70, 60, this);
		controller = new GameController(this);
//		menu = new Menu();

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
		
		//Game Loop - Tracks time and latency to render and update game at a constant 60 ticks per second
		while (running) {
			long now = System.nanoTime();
			delta += (now - initialTime) / nanoseconds;
			initialTime = now;
			if ( delta >= 1 ) {
				update();
				updates++;
				delta--;
			}
			render();
			frames++;
			
			if (System.currentTimeMillis() - timer > 1000) {
				timer+= 1000;
				System.out.println(updates + " TPS, FPS " + frames);
				updates = 0;
				frames = 0;
			}
			try {
				Thread.sleep(0);
			} catch (Exception e) { }
		}
		stop();
	}
	
	//Update Method
	private void update() {
//		if (state == STATE.GAME) {
			starship.update();
			controller.update();
//		}
		
	}
	
	//Render Method
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
		
//		if (state == STATE.GAME) {
			starship.render(g);
			controller.render(g);
//		} else if (state == STATE.MENU) {
//			menu.render(g);
//		}

		g.dispose();
		bs.show();
	}
	
	//Collision Detection Method
	private void checkCollision() {	
		//TODO - Add collision method
		
		//if bullet intersects alien
		//remove alien from screen
		//add points to current score and update the score
	}
	
	//Main
	public static void main( String[] args ) {
		SpaceInvaders game = new SpaceInvaders();
		game.setVisible(true);
		game.start();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
			starship.setSpeed(- GameProperties.CHAR_STEP);
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
			starship.setSpeed(+ GameProperties.CHAR_STEP);
		} else if (e.getKeyCode() == KeyEvent.VK_SPACE && starship.getShooting() == false) {
//			if (state == STATE.GAME) {
				starship.setShooting(true);
				controller.addBullet(new Bullet(starship.getX() + 6, starship.getY() - 35   , this));
//			}
			
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
			starship.setSpeed(0);
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D ) {
			starship.setSpeed(0);
		} else if (e.getKeyCode() == KeyEvent.VK_SPACE ) {
			starship.setShooting(false);
		}
	}

//	@Override
//	public void mouseClicked(MouseEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void mousePressed(MouseEvent e) {
//		if (state == STATE.MENU) {
//			int mx = e.getX();
//			int my = e.getY();
//			
//			if (mx >= GameProperties.SCREEN_WIDTH / 2 -125 && mx <= GameProperties.SCREEN_WIDTH / 2 +125) {
//				if (my >= 250 && my <= 300) {
//					//Play Button Pressed
//					state = STATE.GAME;
//				}
//			}
//		}
//	}

//	@Override
//	public void mouseReleased(MouseEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void mouseEntered(MouseEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void mouseExited(MouseEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
}
