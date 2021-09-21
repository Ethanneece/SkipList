
public class Rectangle {

	private String name; 
	
	private int x, y, w, h; 
	
	private final Rectangle WORLD_BOX = new Rectangle(0, 0, 1024, 1024, "World Box");
	
	/**
	 * 
	 * @param x 
	 * @param y
	 * @param w
	 * @param h
	 * @param name
	 */
	public Rectangle(int x, int y, int w, int h, String name) {
		
		this.name = name; 
		this.x = x; 
		this.y = y; 
		this.w = w; 
		this.h = h;
	}
	
	private boolean isOverlapping(Rectangle out, Rectangle in)
	{
		if(out == in)
		{
			return true; 
		}
		
		return true;
	}
}
