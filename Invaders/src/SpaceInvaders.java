import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	private HighScore highScore;
	
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
		highScore = new HighScore();
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
	
	//Check Win/Loss Condition
	public void checkWinLoss() {
		//if all aliens are destroyed
		if (controller.getAlienCount() == 0) {
			//draw Game Over You Win!
			System.out.println("You Win!");
			System.out.println("Your score is " + controller.getScore() + ".");
			name = JOptionPane.showInputDialog("Please enter your name.");
			score = controller.getScore();
			sql = "INSERT INTO SCORE (NAME, SCORE) VALUES " +
				  "(" + name + ", " + score ")";
		}
			state = STATE.SCORE;
		//else if the aliens reach the invasion line
		} else if (controller.getInvasionLine() == true) {
			//draw Game Over You Lose!
			System.out.println("You Lose!");
			System.out.println("Your score is " + controller.getScore() + ".");
			storeScore();
			state = STATE.SCORE;
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
		
		checkWinLoss();
		
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
		
		//Add SQL Connection
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs;
		
		try {
			
			Class.forName("org.sqlite.JDBC");
			System.out.println("Database driver loaded.");
			
			String dbURL = "jdbc:sqlite:scores.db";
			conn = DriverManager.getConnection(dbURL);
			
			if (conn != null) {
				System.out.println("Connected to database.");
				//Turns off auto commit. Forces a two step process of submitting and then committing.
				conn.setAutoCommit(false);
				
				DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
				System.out.println("Driver name: " + dm.getDriverName());
				System.out.println("Driver version: " + dm.getDriverVersion());
				System.out.println("Product name: " + dm.getDatabaseProductName());
				System.out.println("Product version: " + dm.getDatabaseProductVersion());
				
				stmt = conn.createStatement();
				
				String sql = "CREATE TABLE IF NOT EXISTS SCORE " + 
							 "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
							 " NAME CHAR(50) NOT NULL, " +
							 " SCORE INT NOT NULL, " +
							 " DATE TEXT NOT NULL)";
				
							 " SCORE INT NOT NULL)";				
				stmt.executeUpdate(sql);
				conn.commit();
				
				System.out.println("Score table created successfully.");
				
				System.out.println("");
				rs = stmt.executeQuery("SELECT * FROM SCORE");
				DisplayRecords(rs);
				rs.close();
				
				conn.close();
			}
			
			
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e)
		{
			e.printStackTrace();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		game.setVisible(true);
		game.start();
	}

	private static void DisplayRecords(ResultSet rs) throws SQLException {
		while ( rs.next() ) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			int score = rs.getInt("score");
			String date = rs.getString("date");
			
			System.out.println("ID = " + id);
			System.out.println("Name = " + name);
			System.out.println("Score = " + score);
			System.out.println("Date = " + date);
		}
		
	}

	//Key Listeners
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
			starship.setVelocity(- GameProperties.CHAR_STEP);
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
			starship.setVelocity(+ GameProperties.CHAR_STEP);
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
			starship.setVelocity(0);
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D ) {
			starship.setVelocity(0);
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
					System.exit(1);
				}
			}
		} else if (state == STATE.SCORE) {
			//Play Button
			if (mx >= 175 && mx <= 430) {
				if (my >= 575 && my <= 630) {
					//Pressed Play Button
					state = STATE.GAME;
				}
			}
			
			//Quit Button
			if (mx >= 470 && mx <= 725) {
				if (my >= 575 && my <= 630) {
					//Exit Game
					System.exit(1);
				}
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
