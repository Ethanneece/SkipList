import student.TestCase;
import student.TestableRandom;

public class SkipListTest extends TestCase {
	
	SkipList<String, Rectangle> empty; 
	SkipList<String, Rectangle> small; 
	SkipList<String, Rectangle> medium; 
	SkipList<String, Rectangle> big; 
	
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
	}
	
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
	
	public void testRemove()
	{
		assertEquals(null, empty.remove("rect")); 
		
		Rectangle rect = new Rectangle(1, 1, 1, 1, "rect");
		
		empty.insert("rect", rect);
		
		empty.dump(); 
		assertEquals(rect, empty.remove("rect"));
		
		rect = new Rectangle(20, 20, 20, 20, "20r");
		assertEquals(rect, medium.remove("20r"));
	}
}
