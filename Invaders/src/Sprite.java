import java.awt.Rectangle;

/*
 * Basic character class => width, height, x-position, y-position, filename,
 * and rectangle for collision detection.
 */

public class Sprite {

	//Attributes
	protected int x, y;
	protected int width, height;
	protected String filename;
	protected Rectangle r;
	
	//Getters
	public int getX() { return x; }
	public int getY() { return y; }
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	public String getFilename() { return filename; }
	public Rectangle getRectangle() { return r; }
	
	//Setters
	public void setX(int x) { this.x = x; }
	public void setY(int y) { this.y = y; }
	public void setWidth(int width) { this.width = width; }
	public void setHeight(int height) { this.height = height; }
	public void setFilename(String filename) { this.filename = filename; }
	public void setRectangel(Rectangle r) { this.r = r; }
	
	//Constructors
	public Sprite() {
		super();
		this.x = 0;
		this.y = 0;
		this.width = 0;
		this.height = 0;
		this.filename = "";
		this.r = new Rectangle(this.x, this.y, this.width, this.height);
	}
	
	public Sprite(int x, int y, int width, int height, String filename, Rectangle r) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.filename = filename;
		this.r = new Rectangle(this.x, this.y, this.width, this.height);
	}
	
	public Sprite(int width, int height, String filename) {
		super();
		this.x = 0;
		this.y = 0;
		this.width = width;
		this.height = height;
		this.filename = filename;
		this.r = new Rectangle(this.x, this.y, this.width, this.height);
	}
	
	//Display
	public void Display() {
		System.out.println("x, y is " + this.x + "," + this.y);
	}
}
