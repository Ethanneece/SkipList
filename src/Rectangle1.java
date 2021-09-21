import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Rectangle1 {

	public static void main(String[] args) throws FileNotFoundException {
		
		if(args.length != 1) {
			System.out.println("Invoke with Rectangle1: filename");
			return; 
		}
		
		File file = new File(args[0]);
		Scanner input = new Scanner(file);
		
		while(input.hasNextLine())
		{
			String command = input.nextLine(); 
			
			String[] commands = command.split("\\ +");
			
			if(commands.length == 0)
			{
				
			}
			else if("insert".equals(commands[0]))
			{
				insert(commands);
				System.out.println("insert");
			}
			else if("remove".equals(commands[0]) && commands.length == 2)
			{
				remove(commands[1]);
				System.out.println("remove1");
			}
			else if("remove".equals(commands[0]))
			{
				remove(commands);
				System.out.println("remove2");
			}
			else if("regionsearch".equals(commands[0]))
			{
				regionsearch(commands);
				System.out.println("regionsearch");
			}
			else if("intersections".equals(commands[0]))
			{
				intersections(commands);
				System.out.println("intersections");
			}
			else if("search".equals(commands[0]))
			{
				search(commands);
				System.out.println("search");
			}
			else if("dump".equals(commands[0]))
			{
				dump(commands);
				System.out.println("dump");
			}
		}
	}
	
	public static boolean insert(String[] commands)
	{
		return true; 
	}
	
	public static boolean remove(String name)
	{
		return true;
	}
	
	public static boolean remove(String[] commands)
	{
		return true; 
	}
	
	public static boolean regionsearch(String[] commands)
	{
		return true; 
	}
	
	public static boolean intersections(String[] commands)
	{
		return true; 
	}
	
	public static boolean search(String[] commands)
	{
		return true;
	}
	
	public static boolean dump(String[] commands)
	{
		return true; 
	}
}
