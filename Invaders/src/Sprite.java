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
	
	//Getters
	public int getX() { return x; }
	public int getY() { return y; }
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	public Rectangle getRectangle() { return r; }
	public Boolean getVisible() { return visible; }
	
	//Setters
	public void setX(int x) { this.x = x; }
	public void setY(int y) { this.y = y; }
	public void setWidth(int width) { this.width = width; }
	public void setHeight(int height) { this.height = height; }
	public void setRectangel(Rectangle r) { this.r = r; }
	public void setVisible(Boolean visible) { this.visible = visible;}
	
	//Constructors
	public Sprite() {
		super();
		this.x = 0;
		this.y = 0;
		this.width = 0;
		this.height = 0;
		this.r = new Rectangle(this.x, this.y, this.width, this.height);
		this.visible = true;
	}
	
	public Sprite(int x, int y, int width, int height, Rectangle r, Boolean visible) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.r = new Rectangle(this.x, this.y, this.width, this.height);
		this.visible = true;
	}
	
	public Sprite(int width, int height) {
		super();
		this.x = 0;
		this.y = 0;
		this.width = width;
		this.height = height;
		this.r = new Rectangle(this.x, this.y, this.width, this.height);
		this.visible = true;
	}
	
	public Sprite(int x, int y, int width, int height) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.r = new Rectangle(this.x, this.y, this.width, this.height);
		this.visible = true;
	}
	
	//Display
	public void Display() {
		System.out.println("x, y is " + this.x + "," + this.y);
	}
}
