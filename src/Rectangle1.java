import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Rectangle1 {

	private static SkipList<String, Rectangle> skipList;

	public static void main(String[] args) throws FileNotFoundException {
		
		if(args.length != 1) {
			System.out.println("Invoke with Rectangle1: filename");
			return; 
		}
		
		File file = new File(args[0]);
		Scanner input = new Scanner(file);
		skipList = new SkipList<>();

		while(input.hasNextLine())
		{
			String command = input.nextLine().trim();
			
			String[] commands = command.split("\\s+");
			
			if(commands.length == 0)
			{
				//whitespace.
			}
			else if("insert".equals(commands[0]))
			{
				insert(commands);
			}
			else if("remove".equals(commands[0]) && commands.length == 2)
			{
				remove(commands[1]);
			}
			else if("remove".equals(commands[0]))
			{
				remove(commands);
			}
			else if("regionsearch".equals(commands[0]))
			{
				regionsearch(commands);
			}
			else if("intersections".equals(commands[0]))
			{
				intersections(commands);
			}
			else if("search".equals(commands[0]))
			{
				search(commands);
			}
			else if("dump".equals(commands[0]))
			{
				dump(commands);
			}
		}
	}
	
	public static boolean insert(String[] commands)
	{
		if (commands.length != 6)
		{
			System.out.println("command does not have enough parameters");
			return false;
		}

		Rectangle rect = new Rectangle(Integer.parseInt(commands[2]),
				Integer.parseInt(commands[3]), Integer.parseInt(commands[4]),
				Integer.parseInt(commands[5]), commands[1]);

		if(!rect.isValid())
		{
			System.out.println("Rectangle rejected: " + rect);
			return false;
		}

		skipList.insert(commands[1], rect);

		System.out.println("Rectangle inserted: " + rect);

		return true;
	}
	
	public static boolean remove(String name)
	{
		Rectangle rect = skipList.remove(name);

		if(rect == null)
		{
			System.out.println("Rectangle not removed: ("  + name + ")");
		}
		else
		{
			System.out.println("Rectangle removed: " + rect);
		}
		return true;
	}
	
	public static boolean remove(String[] commands)
	{
		if(commands.length != 5)
		{
			System.out.println("command does not have enough parameters");
			return false;
		}

		Rectangle rect = new Rectangle(Integer.parseInt(commands[1]), Integer.parseInt(commands[2]),
				Integer.parseInt(commands[3]), Integer.parseInt(commands[4]), "");


		if (!rect.isValid())
		{
			System.out.println("Rectangle rejected: " + rect.noName());
			return false;
		}

		Rectangle result = skipList.remove(rect, 0);

		if (result == null)
		{
			System.out.println("Rectangle not removed: " + rect.noName());
			return false;
		}

		System.out.println("Rectangle removed: " + result);
		return true; 
	}
	
	public static boolean regionsearch(String[] commands)
	{
		if (commands.length != 5)
		{
			System.out.println("command does not have enough parameters");
		}



		Rectangle rect = new Rectangle(Integer.parseInt(commands[1]), Integer.parseInt(commands[2]),
				Integer.parseInt(commands[3]), Integer.parseInt(commands[4]), "");

		if (!rect.hasDimensions())
		{
			System.out.println("Rectangle rejected: " + rect.noName());
			return false;
		}

		ArrayList<Rectangle> rectangles = skipList.regionSearch(rect);
		System.out.println("Rectangles intersecting region " + rect.noName());
		for (int i = 0; i < rectangles.size(); i++) {
			System.out.println(rectangles.get(i));
		}
		return true; 
	}
	
	public static boolean intersections(String[] commands)
	{
		ArrayList<Rectangle> rectangles = skipList.intersections();
		System.out.println("Intersection pairs: ");
		for (int i = 0; i < rectangles.size(); i += 2)
		{
			String rect1 = rectangles.get(i).toString();
			String rect2 = rectangles.get(i + 1).toString();
			System.out.println(rect1.substring(0, rect1.length() -1) + " | " + rect2.substring(1));
			System.out.println(rect2.substring(0, rect2.length() -1) + " | " + rect1.substring(1));
		}
		return true; 
	}
	
	public static boolean search(String[] commands)
	{
		if (commands.length < 2)
		{
			System.out.println("command does not have enough parameters");
		}

		ArrayList rectangles = skipList.search(commands[1]);

		if (rectangles.size() == 0)
		{
			System.out.println("Rectangle not found: " + commands[1]);
			return false;
		}
		System.out.println("Rectangles found: ");
		for(int i = 0; i < rectangles.size(); i++)
		{
			System.out.println(rectangles.get(i));
		}

		return true;
	}
	
	public static boolean dump(String[] commands)
	{
		System.out.println("SkipList dump: ");
		skipList.dump();
		return true;
	}
}
