import java.awt.Rectangle;

/*
 * Basic character class => width, height, x-position, y-position, rectangle for collision detection, visible,.
 */

public class Sprite {

	//Attributes
	protected int x, y;
	protected int width, height;
	protected Rectangle r;
	protected Boolean visible, moving, dying;
	protected String filename;
	
	//Getters
	public int getX() { return this.x; }
	public int getY() { return this.y; }
	public int getWidth() { return this.width; }
	public int getHeight() { return this.height; }
	public Rectangle getRectangle() { return this.r; }
	public Boolean getVisible() { return this.visible; }
	public String getFilename() { return this.filename; }
	
	//Setters
	public void setX(int x) { this.x = x; this.r.setLocation(this.x, this.y); }
	public void setY(int y) { this.y = y; this.r.setLocation(this.x, this.y); }
	public void setWidth(int width) { this.width = width; this.r.setSize(this.width, this.height); }
	public void setHeight(int height) { this.height = height; this.r.setSize(this.width, this.height); }
	public void setRectangle(Rectangle r) { this.r = r; }
	public void setVisible(Boolean visible) { this.visible = visible;}
	public void setFilename(String filename) { this.filename = filename; }
	
	//Constructors
	public Sprite() {
		super();
		this.x = 0;
		this.y = 0;
		this.width = 0;
		this.height = 0;
		this.r = new Rectangle(this.x, this.y, this.width, this.height);
		this.visible = true;
		this.filename = "";
	}
	
	public Sprite(int x, int y, int width, int height, Rectangle r, Boolean visible, String filename) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.r = new Rectangle(this.x, this.y, this.width, this.height);
		this.visible = true;
		this.filename = filename;
	}
	
	public Sprite(int width, int height) {
		super();
		this.x = 0;
		this.y = 0;
		this.width = width;
		this.height = height;
		this.r = new Rectangle(this.x, this.y, this.width, this.height);
		this.visible = true;
		this.filename = "";
	}
	
	public Sprite(int x, int y, int width, int height) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.r = new Rectangle(this.x, this.y, this.width, this.height);
		this.visible = true;
		this.filename = "";
	}
	
	//Display
	public void Display() {
		System.out.println("x, y is " + this.x + "," + this.y);
	}
}