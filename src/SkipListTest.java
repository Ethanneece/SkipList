import student.TestCase;
import student.TestableRandom;

import java.util.ArrayList;

public class SkipListTest extends TestCase {
	
	private SkipList<String, Rectangle> empty;
	private SkipList<String, Rectangle> small;
	private SkipList<String, Rectangle> medium;
	private SkipList<String, Rectangle> big;
	private SkipList<String, Rectangle> duplicateKeys;

	/**
	 * Sets up testing for the SkipList.
	 */
	public void setUp() {
		 empty = new SkipList<>();
		 
		 small = new SkipList<>(); 
		 
		 for(int i = 0; i < 10; i++)
		 {
			 Rectangle rect = new Rectangle(i, i, i, i, "ro" + i);
			 small.insert("ro" + i, rect);
		 }
		 
		 medium = new SkipList<>(); 
		 for(int i = 0; i < 100; i++)
		 {
			 Rectangle rect = new Rectangle(i, i, i, i, i +"r");
			 medium.insert(i + "r", rect);
		 }
		 
		 big = new SkipList<>(); 
		 for(int i = 0; i < 1000; i++)
		 {
			 Rectangle rect = new Rectangle(i, i, i, i, "r" + i); 
			 big.insert("r" + i, rect);
		 }

		 duplicateKeys = new SkipList<>();
		 for(int i = 0; i < 10; i++)
		 {
		 	Rectangle rect = new Rectangle(i, i, i, i, "rect");
		 	duplicateKeys.insert("rect", rect);
		 }
	}

	/**
	 * Test the insert method in SkipList.
	 */
	public void testInsert() {
		
		Rectangle rect1 = new Rectangle(5, 3, 2, 1, "rect1");
		Rectangle rect2 = new Rectangle(10, 20, 10, 10, "rect2");
		Rectangle rect3 = new Rectangle(1, 1, 1, 1, "rect3");
		Rectangle rect4 = new Rectangle(6, 6, 6, 6, "rect4");
		
		TestableRandom.setNextInts(2, 2, 2, 1, 2, 2, 1, 2, 2, 2, 2, 1, 1);
		
		empty.insert("rect1", rect1);
		empty.insert("rect2", rect2);
		empty.insert("rect3", rect3);
		empty.insert("rect4", rect4);
		
		empty.dump(); 
		
		assertFuzzyEquals("Node has depth 5, Value (null)\n"
				+ "Node has depth 4, Value (rect1, 5, 3, 2, 1)\n"
				+ "Node has depth 3, Value (rect2, 10, 20, 10, 10)\n"
				+ "Node has depth 5, Value (rect3, 1, 1, 1, 1)\n"
				+ "Node has depth 1, Value (rect4, 6, 6, 6, 6)\n"
				+ "SkipList size is: 4", systemOut().getHistory());
		
		systemOut().clearHistory(); 
		
		Rectangle rect6 = new Rectangle(1, 2, 3, 4, "rect6");
		Rectangle rect7 = new Rectangle(3, 2, 3, 4, "rect");
		Rectangle rect8 = new Rectangle(5, 10, 20, 30, "rect24");
		Rectangle rect9 = new Rectangle(7, 2, 3, 5, "rect44");
		
		TestableRandom.setNextInts(1, 2, 1, 2, 2, 1, 2, 2, 2, 1);
		
		empty.insert("rect6", rect6);
		empty.insert("rect", rect7);
		empty.insert("rect24", rect8);
		empty.insert("rect44", rect9);
		
		empty.dump(); 
		
		assertFuzzyEquals("Node has depth 5, Value (null)\n"
				+ "Node has depth 2, Value (rect, 3, 2, 3, 4)\n"
				+ "Node has depth 4, Value (rect1, 5, 3, 2, 1)\n"
				+ "Node has depth 3, Value (rect2, 10, 20, 10, 10)\n"
				+ "Node has depth 3, Value (rect24, 5, 10, 20, 30)\n"
				+ "Node has depth 5, Value (rect3, 1, 1, 1, 1)\n"
				+ "Node has depth 1, Value (rect4, 6, 6, 6, 6)\n"
				+ "Node has depth 4, Value (rect44, 7, 2, 3, 5)\n"
				+ "Node has depth 1, Value (rect6, 1, 2, 3, 4)\n"
				+ "SkipList size is: 8", systemOut().getHistory());
	}

	/**
	 * Tests the remove method in SkipList.
	 */
	public void testRemove()
	{
		assertEquals(null, empty.remove("rect")); 
		
		Rectangle rect = new Rectangle(1, 1, 1, 1, "rect");
		
		empty.insert("rect", rect);

		assertEquals(rect, empty.remove("rect"));

		rect = new Rectangle(20, 20, 20, 20, "20r");
		assertEquals(rect, medium.remove("20r"));

		for(int i = 999; i >= 0; i--)
		{
			rect = new Rectangle(i, i, i, i, "r" + i);
			assertEquals(rect, big.remove("r" + i));
		}
	}

	/**
	 * Test the removexywh method in SkipList.
	 */
	public void testRemovexywh()
	{
		Rectangle rect = new Rectangle(1, 1, 1, 1, "rect");
		assertEquals(null, empty.remove(rect, 0 ));

		empty.insert("rect", rect);

		assertEquals(rect, empty.remove(rect, 0));

		rect = new Rectangle(20, 20, 20, 20, "20r");
		assertEquals(rect, medium.remove(rect, 0));

		rect = new Rectangle(200, 200, 200, 200, "r200");
		assertEquals(rect, big.remove(rect, 0));
	}

	/**
	 * Test the regionSearch method in SkipList.
	 */
	public void testRegionSearch()
	{
		Rectangle rect = new Rectangle(1, 1, 1, 1, "");
		assertEquals(empty.regionSearch(rect).size(), 0);

		empty.insert("rect", rect);
		assertEquals(empty.regionSearch(rect).get(0), rect);

		rect = new Rectangle(-1, -1, 100, 100, "");
		assertEquals(small.regionSearch(rect).size(), 10);

		rect = new Rectangle(-1, -1, 5, 5, "");
		assertEquals(small.regionSearch(rect).size(), 4);

		rect = new Rectangle(-1, -1, 20, 20, "");
		assertEquals(medium.regionSearch(rect).size(), 19);
	}

	/**
	 * test the intersection method in SkipList.
	 */
	public void testIntersection()
	{
		assertEquals(empty.intersections().size(), 0 );

		assertEquals(32, small.intersections().size());

		assertEquals(4802, medium.intersections().size());
	}

	/**
	 * Test the search method in SkipList.
	 */
	public void testSearch()
	{
		assertEquals(empty.search("rect").size(), 0);

		assertEquals(duplicateKeys.search("rect").size(), 10);

		for(int i = 0; i < 10; i++)
		{
			Rectangle rect = new Rectangle(i, i, i, i, i + "rect");
			duplicateKeys.insert(i + "rect", rect);
		}

		assertEquals(duplicateKeys.search("rect").size(), 10);

		assertEquals(big.search("r30").size(), 1);
	}
}
