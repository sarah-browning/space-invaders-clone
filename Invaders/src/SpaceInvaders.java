import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class SpaceInvaders extends JFrame implements KeyListener, MouseListener, Runnable {
	
	//Attributes
	private static final long serialVersionUID = 9121643710941167328L;
	private Boolean running = false;
	private Thread t;
	private BufferedImage background;
	private Starship starship;
	private GameController controller;
	private Menu menu;
	
	private STATE state = STATE.MENU;
	
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
		background = loader.loadImage("res/starbkg.jpg");
		
		//Load Components
		addKeyListener(this);
		addMouseListener(this);
		starship = new Starship( 70, 60, this);
		controller = new GameController(this);
		menu = new Menu();
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
//				System.out.println(updates + " TPS, FPS " + frames);
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
		if (state == STATE.GAME) {
			starship.update();
			controller.update();
		}
		
	}
	
	//Render Method
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
		
		if (state == STATE.MENU) {
			menu.render(g);
		} else if (state == STATE.GAME) {
			starship.render(g);
			controller.render(g);
		} else if (state == STATE.SCORE) {
			//TODO - Add score screen
		}

		g.dispose();
		bs.show();
	}
	
	//Main
	public static void main( String[] args ) {
		SpaceInvaders game = new SpaceInvaders();
		game.setVisible(true);
		game.start();
	}

	
	//Key Listeners
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
			if (state == STATE.GAME) {
				starship.setShooting(true);
				controller.addBullet(new Bullet((int)starship.getX() + 20, (int)starship.getY() - 40, this));
			}
			
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

	//Mouse Listeners
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		int mx = e.getX();
		int my = e.getY();
		
		//Play Button
		if (mx >= 500 && mx <= 755) {
			if (my >= 300 && my <= 355) {
				//Pressed Play Button
				state = STATE.GAME;
			}
		}
		
		//Score Button
		if (mx >= 500 && mx <= 755) {
			if (my >= 375 && my <= 630) {
				//Pressed Score button
				state = STATE.SCORE;
			}
		}
		
		//Quit Button
		if (mx >= 500 && mx <= 755) {
			if (my >= 450 && my <= 705) {
				//Exit Game
				System.exit(1);
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
