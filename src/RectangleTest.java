import student.TestCase;

/**
 * Class for testing the functionality of Rectangle
 * 
 * @author Ethan Neece (ethann)
 * @version 9/23/2021
 */
public class RectangleTest extends TestCase
{

    private Rectangle zero;
    private Rectangle small;
    private Rectangle medium;
    private Rectangle big;
    private Rectangle huge;

    /**
     * sets up the Rectangles to be tested.
     */
    public void setUp()
    {
        zero = new Rectangle(0, 0, 0, 0, "zero");
        small = new Rectangle(0, 0, 10, 10, "small");
        medium = new Rectangle(0, 0, 100, 100, "medium");
        big = new Rectangle(0, 0, 1000, 1000, "big");
        huge = new Rectangle(0, 0, 100000, 100000, "huge");
    }

    /**
     * Tests the isValid() method in Rectangle.
     */
    public void testIsValid()
    {
        assertFalse(zero.isValid());
        assertTrue(small.isValid());
        assertTrue(medium.isValid());
        assertTrue(big.isValid());
        assertFalse(huge.isValid());
    }

    /**
     * Tests the hasDimensions() method in Rectangle.
     */
    public void testHasDimensions()
    {
        assertFalse(zero.hasDimensions());
        assertTrue(small.hasDimensions());
        assertTrue(huge.hasDimensions());
    }

    /**
     * Tests the equals() method in Rectangle.
     */
    public void testEquals()
    {
        assertTrue(zero.equals(zero));

        assertFalse(zero.equals(null));

        assertFalse(zero.equals(""));

        assertFalse(zero.equals(small));

        Rectangle zerocpy = new Rectangle(0, 0, 0, 0, "zero");

        assertTrue(zero.equals(zerocpy));
    }

    /**
     * Tests the toString() method in Rectangle.
     */
    public void testToString()
    {
        assertEquals("(zero, 0, 0, 0, 0)", zero.toString());
        assertEquals("(small, 0, 0, 10, 10)", small.toString());
        assertEquals("(big, 0, 0, 1000, 1000)", big.toString());
    }

    /**
     * Test the noName() method in Rectangle.
     */
    public void testNoName()
    {
        assertEquals("(0, 0, 0, 0)", zero.noName());
        assertEquals("(0, 0, 10, 10)", small.noName());
        assertEquals("(0, 0, 1000, 1000)", big.noName());
    }

    /**
     * Test the compareTo() method in Rectangle.
     */
    public void testCompareTo()
    {
        assertEquals(0, zero.compareTo(zero));

        Rectangle zerocpy = new Rectangle(0, 0, 0, 0);

        assertEquals(0, zerocpy.compareTo(zero));

        assertEquals(1, big.compareTo(medium));
        assertEquals(-1, big.compareTo(zero));
    }

}
