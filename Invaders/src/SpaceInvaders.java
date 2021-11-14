import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class SpaceInvaders extends JFrame implements KeyListener, MouseListener, Runnable {
	
	//Attributes
	private static final long serialVersionUID = 9121643710941167328L;
	
	private Boolean running = false;
	private Thread t;
	private BufferedImage background;
	private Starship starship;
	private GameController controller;
	private Menu menu;
	private HighScore highScore;
	private static Database db = new Database();
	private STATE state;
	
	//Prepare GUI
	public SpaceInvaders() {
		super("Space Invaders");
		setSize(GameProperties.SCREEN_WIDTH, GameProperties.SCREEN_HEIGHT);
		setLocationRelativeTo(null);
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
		highScore = new HighScore();
		state = STATE.MENU;
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
			db.closeConnection();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.exit(1);
	}
	
	//Check Win/Loss Condition
	public void checkWinLoss() {
		String name;
		int score;
		if (state == STATE.GAME) {
			//if all aliens are destroyed
			if (controller.getAlienCount() == 0) {
				System.out.println("You Win!");
				System.out.println("Your score is " + controller.getScore() + ".");
				name = JOptionPane.showInputDialog("Congratulations! You won! Please enter your name to save your score.");
				state = STATE.SCORE;
				score = controller.getScore();
				db.recordScore(name, score);
				db.retrieveResults();
				
			//else if the aliens reach the invasion line
			} else if (controller.getInvasionLine() == true) {
				System.out.println("You Lose!");
				System.out.println("Your score is " + controller.getScore() + ".");
				name = JOptionPane.showInputDialog("GAME OVER! Sorry, you lost. Please enter your name to save your score.");
				state = STATE.SCORE;
				score = controller.getScore();
				db.recordScore(name, score);
				db.retrieveResults();
			}
		}
	}
	
	//Reset Game State
	public void reset() {
		if (state == STATE.SCORE) {
			getContentPane().removeAll();
			init();
			
//			main(null);
		}
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
			try {
				render();
			} catch (IOException e) {
				e.printStackTrace();
			}
			frames++;
			
			if (System.currentTimeMillis() - timer > 1000) {
				timer+= 1000;
//				System.out.println(updates + " TPS, FPS " + frames);
				updates = 0;
				frames = 0;
			}
			try {
				Thread.sleep(25);
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
		
		checkWinLoss();
		
	}
	
	//Render Method
	private void render() throws IOException {
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
			highScore.render(g);
		} else if (state == STATE.END) {
//			gameOver.render(g);
		}

		g.dispose();
		bs.show();
	}
	
	//Main
	public static void main( String[] args ) {
		SpaceInvaders game = new SpaceInvaders();
		
		db.connect();
		db.createTable();
		
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
			starship.setVelocity(- GameProperties.CHAR_STEP * 2);
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
			starship.setVelocity(+ GameProperties.CHAR_STEP * 2);
		} else if (e.getKeyCode() == KeyEvent.VK_SPACE && starship.getIsShooting() == false) {
			if (state == STATE.GAME) {
				starship.setIsShooting(true);
				controller.addBullet(new Bullet((int)starship.getX() + 24, (int)starship.getY() - 10, this));
			}
			
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
			starship.setVelocity(0);
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D ) {
			starship.setVelocity(0);
		} else if (e.getKeyCode() == KeyEvent.VK_SPACE ) {
			starship.setIsShooting(false);
		}
	}

	//Mouse Listeners
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();

		if (state == STATE.MENU) {
			
			//Play Button
			if (mx >= 500 && mx <= 755) {
				if (my >= 250 && my <= 305) {
					//Pressed Play Button
					state = STATE.GAME;
				}
			}
			
			//Score Button
			if (mx >= 500 && mx <= 755) {
				if (my >= 325 && my <= 380) {
					//Pressed Score button
					state = STATE.SCORE;
				}
			}
			
			//Quit Button
			if (mx >= 500 && mx <= 755) {
				if (my >= 400 && my <= 455) {
					//Exit Game
					db.closeConnection();
					System.exit(1);
				}
			}
		} else if (state == STATE.SCORE) {
			//Quit Button
			if (mx >= 322 && mx <= 577) {
				if (my >= 575 && my <= 630) {
					//Exit Game
					db.closeConnection();
					System.exit(1);
				}
			}
			
//			//Menu Button
//			if (mx >= 175 && mx <= 430) {
//				if (my >= 575 && my <= 630) {
//					//Pressed Menu Button
//					state = STATE.MENU;
//					reset();
//				}
//			}
			
//			//Quit Button
//			if (mx >= 470 && mx <= 725) {
//				if (my >= 575 && my <= 630) {
//					//Exit Game
//					db.closeConnection();
//					System.exit(1);
//				}
//			}
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
	
	//Getters
	public Boolean getRunning() {
		return running;
	}

	public Thread getT() {
		return t;
	}

	public Starship getStarship() {
		return starship;
	}

	public GameController getController() {
		return controller;
	}

	public Menu getMenu() {
		return menu;
	}

	public HighScore getHighScore() {
		return highScore;
	}

	public static Database getDb() {
		return db;
	}

	//Setters
	public void setRunning(Boolean running) {
		this.running = running;
	}

	public void setT(Thread t) {
		this.t = t;
	}

	public void setStarship(Starship starship) {
		this.starship = starship;
	}

	public void setController(GameController controller) {
		this.controller = controller;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public void setHighScore(HighScore highScore) {
		this.highScore = highScore;
	}

	public static void setDb(Database db) {
		SpaceInvaders.db = db;
	}
	
}
