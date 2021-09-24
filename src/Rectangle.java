/**
 * Rectangle class using x, y, w, h, and a name.
 * 
 * @author Ethan Neece (ethann)
 * @version 9/23/2021
 * 
 */
public class Rectangle implements Comparable<Rectangle>
{

    private String name;

    private int x;
    private int y;
    private int w;
    private int h;

    private static final Rectangle WORLD_BOX = new Rectangle(0, 0, 1024, 1024);

    /**
     * Creates an object of Rectangle.
     * 
     * @param x    x-coordinate of the rectangle.
     * @param y    y-coordinate of the rectangle.
     * @param w    width of the rectangle.
     * @param h    height of the rectangle.
     * @param name name of the rectangle.
     */
    public Rectangle(int x, int y, int w, int h, String name)
    {

        this(x, y, w, h);

        this.name = name;
    }

    /**
     * Creates an object of rectangle.
     * 
     * @param x x-coordinate of the rectangle.
     * @param y y-coordinate of the rectangle.
     * @param w width of the rectangle.
     * @param h height of the rectangle.
     */
    public Rectangle(int x, int y, int w, int h)
    {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    /**
     * checks if the rectangle is overlapping or not.
     * 
     * @param rect thing being compared to.
     * @return true if the rectangle is overlapping, and false otherwise.
     */
    private boolean isOverlapping(Rectangle rect)
    {
        if (this.x >= rect.x + rect.w || rect.x >= this.x + this.w)
        {
            return false;
        }

        return !(this.y >= rect.y + rect.h || rect.y >= this.y + this.h);
    }

    /**
     * Checks if the dimensions for this rectangle isInside the world box.
     * 
     * @return true if its inside the workbox and has correct dimensions, false
     *         otherwise
     * 
     */
    public boolean isValid()
    {
        if (x < 0 || y < 0 || w <= 0 || h <= 0)
        {
            return false;
        }

        return !(this.x < WORLD_BOX.x || this.y < WORLD_BOX.y
                || this.x + this.w > WORLD_BOX.x + WORLD_BOX.w
                || this.y + this.h > WORLD_BOX.y + WORLD_BOX.h);
    }

    /**
     * 
     * @return true if the dimensions are possible, false otherwise
     */
    public boolean hasDimensions()
    {
        return w > 0 && h > 0;
    }

    /**
     * returns true if it is the same object or all fields are the same.
     * 
     * @param o thing being compared to.
     * @return true if the objects are the same, false otherwise.
     */
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }

        if (o == null)
        {
            return false;
        }

        if (o.getClass() == this.getClass())
        {
            Rectangle rect = (Rectangle) o;

            return (name.equals(rect.name) && x == rect.x && y == rect.y
                    && h == rect.h && w == rect.w);
        }

        return false;
    }

    /**
     *
     * @return a String representation of the rectangle object.
     */
    public String toString()
    {
        return "(" + name + ", " + x + ", " + y + ", " + w + ", " + h + ")";
    }

    /**
     * 
     * @return Rectangles dimensions without including the name given.
     */
    public String noName()
    {
        return "(" + x + ", " + y + ", " + w + ", " + h + ")";
    }

    /**
     * compares 2 rectangles with each other.
     * 
     * @param rect the other thing being compared with.
     * @return 0 if they are equal, 1 if they overlap, or -1 if neither.
     */
    @Override
    public int compareTo(Rectangle rect)
    {

        if (this == rect)
        {
            return 0;
        }

        if (x == rect.x && y == rect.y && h == rect.h && w == rect.w)
        {
            return 0;
        }

        if (isOverlapping(rect))
        {
            return 1;
        }

        return -1;
    }
}
