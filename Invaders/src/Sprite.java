import java.awt.Rectangle;

/*
 * Basic character class => width, height, x-position, y-position, rectangle for collision detection, visible,.
 */

public class Sprite extends Rectangle{

	//Attributes
	protected Boolean visible, moving, dying;
	protected String filename;
	
	//Getters
	public Boolean getVisible() { return this.visible; }
	public String getFilename() { return this.filename; }
	
	//Setters
	public void setX(int x) { setLocation(x, this.y); }
	public void setY(int y) { setLocation(this.x, y); }
	public void setWidth(int width) { setSize(width, this.height); }
	public void setHeight(int height) { setSize(this.width, height); }
	public void setVisible(Boolean visible) { this.visible = visible;}
	public void setFilename(String filename) { this.filename = filename; }
	
	//Constructors
	public Sprite() {
		this.visible = true;
		this.filename = "";
	}
	
	public Sprite(int x, int y, int width, int height, Boolean visible, String filename) {
		super(x, y, width, height);
		this.visible = true;
		this.filename = filename;
	}
	
	public Sprite(int width, int height) {
		super(width, height);
		this.visible = true;
		this.filename = "";
	}
	
	public Sprite(int x, int y, int width, int height) {
		super(x, y, width, height);
		this.visible = true;
		this.filename = "";
	}
	
	//Display
	public void Display() {
		System.out.println("x, y is " + this.x + "," + this.y);
	}
}