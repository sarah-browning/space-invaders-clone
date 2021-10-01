import java.awt.Rectangle;

/*
 * Basic character class => width, height, x-position, y-position, filename of graphic
 * Additional properties will apply to starship and alien specific classes, which will inherit from Sprite
 */


public class Sprite {
	
	/**
	 * data members
	 */

	protected int x, y;				//upper-left coordinate of the object (pixels are integers)
	protected int width, height;	//size of the object
	protected String filename;		//location of graphic file
	protected Rectangle r;			//Rectangle class: will be used to aid in collision detection;
	
	//Getters and Setters
	public int getX() { return x; }
	public int getY() { return y; }
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	public String getFilename() { return filename; }
	public Rectangle getRectangle() { return this.r; }
		
	public void setX(int x) { this.x = x; this.r.setLocation(this.x, this.y);}
	public void setY(int y) { this.y = y; this.r.setLocation(this.x, this.y);}
	public void setWidth(int width) { this.width = width; this.r.setSize(this.width, this.height); }
	public void setHeight(int height) { this.height = height; this.r.setSize(this.width, this.height); }
	public void setFilename(String filename) { this.filename = filename; }
			
	//Default Constructor
	public Sprite() {
		super();
		this.x = 0;
		this.y = 0;
		this.width = 0;
		this.height = 0;
		this.filename = "";
		this.r = new Rectangle(this.x, this.y, this.width, this.height);
	}
		
	//Secondary Constructors
	public Sprite(int x, int y, int width, int height, String filename) {
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
		this.r = new Rectangle(this.x, this.y, this.width, this.height); //Rectangle has to be filled at bottom of constructor so that x,y,width, and height have already been set by constructor
	}
	
	public void display() {
		System.out.println("x,y is " + this.x + "," + this.y);
	}
}