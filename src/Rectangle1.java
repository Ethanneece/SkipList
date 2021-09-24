import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

//On my honor:
//
//- I have not used source code obtained from another student,
//or any other unauthorized source, either modified or
//unmodified.
//
//- All source code and documentation used in my program is
//either my original work, or was derived by me from the
//source code published in the textbook for this course.
//
//- I have not discussed coding details about this project with
//anyone other than the instructor, ACM/UPE tutors, programming 
//partner (if allowed in this class), or the TAs assigned to 
//this course. I understand that I may discuss the concepts
//of this program with other students, and that another student
//may help me debug my program so long as neither of us writes
//anything during the discussion or modifies any computer file
//during the discussion. I have violated neither the spirit nor
//letter of this restriction.

/**
 * Invocation: Rectangle1 <filename>
 * 
 * Class used to launch program. Takes commands from text file and completes
 * them till the text file is out of commands. has functionality with
 * rectangles.
 * 
 * Commands this program can run: insert <name> <x> <y> <w> <h> remove <name>
 * remove <x> <y> <w> <h> regionsearch <x> <y> <w> <h> intersections search
 * <name> dump
 * 
 * @author Ethan Neece (ethann)
 * @version 9/23/2021
 * 
 */
public class Rectangle1
{

    private static SkipList<String, Rectangle> skipList;

    /**
     * Reads commands from filename given through the parameter and completes
     * them till the file is out of commands.
     * 
     * @param args has to be 1 argument which is the filename.
     * @throws FileNotFoundException if the filename is wrong or missing will be
     *                               thrown.
     */
    public static void main(String[] args) throws FileNotFoundException
    {

        if (args.length != 1)
        {
            System.out.println("Invoke with Rectangle1: filename");
            return;
        }

        File file = new File(args[0]);
        Scanner input = new Scanner(file);
        skipList = new SkipList<>();

        while (input.hasNextLine())
        {
            String command = input.nextLine().trim();

            String[] commands = command.split("\\s+");

            if (commands.length == 0)
            {
                // getting rid of the blank lines that might be in commands.
                // Style checker mad at me for leaving blank.
                int x = 0;
            }
            else if ("insert".equals(commands[0]))
            {
                insert(commands);
            }
            else if ("remove".equals(commands[0]) && commands.length == 2)
            {
                remove(commands[1]);
            }
            else if ("remove".equals(commands[0]))
            {
                remove(commands);
            }
            else if ("regionsearch".equals(commands[0]))
            {
                regionsearch(commands);
            }
            else if ("intersections".equals(commands[0]))
            {
                intersections();
            }
            else if ("search".equals(commands[0]))
            {
                search(commands);
            }
            else if ("dump".equals(commands[0]))
            {
                dump();
            }
        }

        input.close();
    }

    /**
     * Inserts the Rectangle into the Skip List. Does not insert on the
     * following conditions: commands did not have the right amount of
     * parameters. rectangle was not in the world box or had invalid x, y, w, h.
     * 
     * Outputs whether or not the rectangle succeeded to be put into the
     * SkipList by printing it out.
     * 
     * @param commands parameters of the rectangle.
     */
    public static void insert(String[] commands)
    {
        if (commands.length != 6)
        {
            System.out.println("command does not have enough parameters");
            return;
        }

        Rectangle rect = new Rectangle(Integer.parseInt(commands[2]),
                Integer.parseInt(commands[3]), Integer.parseInt(commands[4]),
                Integer.parseInt(commands[5]), commands[1]);

        if (!rect.isValid())
        {
            System.out.println("Rectangle rejected: " + rect);
            return;
        }

        skipList.insert(commands[1], rect);

        System.out.println("Rectangle inserted: " + rect);
    }

    /**
     * Removes a rectangle from the SkipList based on the name. Does not remove
     * under the following conditions: Rectangle was not in the SkipList.
     * 
     * @param name is the name of the rectangle that would be removed.
     */
    public static void remove(String name)
    {
        Rectangle rect = skipList.remove(name);

        if (rect == null)
        {
            System.out.println("Rectangle not removed: (" + name + ")");
        }
        else
        {
            System.out.println("Rectangle removed: " + rect);
        }
    }

    /**
     * Removes a rectangle from the SkipList based on x, y, w ,h. Does not
     * remove under the following conditions: Did not have the correct amount of
     * parameters. Rectangle was not valid in the world box. Rectangle was not
     * in the Skip List.
     * 
     * @param commands is the x, y, w, h of the rectangle.
     */
    public static void remove(String[] commands)
    {
        if (commands.length != 5)
        {
            System.out.println("command does not have enough parameters");
            return;
        }

        Rectangle rect = new Rectangle(Integer.parseInt(commands[1]),
                Integer.parseInt(commands[2]), Integer.parseInt(commands[3]),
                Integer.parseInt(commands[4]));

        if (!rect.isValid())
        {
            System.out.println("Rectangle rejected: " + rect.noName());
            return;
        }

        Rectangle result = skipList.removeValue(rect);

        if (result == null)
        {
            System.out.println("Rectangle not removed: " + rect.noName());
            return;
        }

        System.out.println("Rectangle removed: " + result);
    }

    /**
     * Does a region search based on the rectangle given. Does not do region
     * search under the following conditions: commands does not have the correct
     * parameters. rectangle has dimensions that are not valid.
     * 
     * Prints out rectangles that falls under the region given by commands.
     * 
     * @param commands is the x, y, h, w of the rectangle.
     */
    public static void regionsearch(String[] commands)
    {
        if (commands.length != 5)
        {
            System.out.println("command does not have enough parameters");
        }

        Rectangle rect = new Rectangle(Integer.parseInt(commands[1]),
                Integer.parseInt(commands[2]), Integer.parseInt(commands[3]),
                Integer.parseInt(commands[4]));

        if (!rect.hasDimensions())
        {
            System.out.println("Rectangle rejected: " + rect.noName());
            return;
        }

        ArrayList<Rectangle> rectangles = skipList.regionSearch(rect);
        System.out.println(
                "Rectangles intersecting region " + rect.noName() + ": ");
        for (int i = 0; i < rectangles.size(); i++)
        {
            System.out.println(rectangles.get(i));
        }
    }

    /**
     * Finds all the intersections of the rectangles in the Skip List.
     * 
     * prints all the rectangles that overlap with each other.
     */
    public static void intersections()
    {
        ArrayList<Rectangle> rectangles = skipList.intersections();
        System.out.println("Intersections pairs:");
        for (int i = 0; i < rectangles.size(); i += 2)
        {
            String rect1 = rectangles.get(i).toString();
            String rect2 = rectangles.get(i + 1).toString();
            
            //My solution didn't count duplicates like (r1 | r2) (r2 | r1)
            // so I'm just printing out the same pair in reversed. 
            System.out.println(rect1.substring(0, rect1.length() - 1) + " | "
                    + rect2.substring(1));

            System.out.println(rect2.substring(0, rect2.length() - 1) + " | "
                    + rect1.substring(1));
        }
    }

    /**
     * Searches for rectangles that have the name given by commands.
     * 
     * Prints out all the rectangles with the given name. If no rectangles are
     * found prints not found message.
     * 
     * @param commands is the name given to search for.
     */
    public static void search(String[] commands)
    {
        if (commands.length < 2)
        {
            System.out.println("command does not have enough parameters");
        }

        ArrayList<Rectangle> rectangles = skipList.search(commands[1]);

        if (rectangles.size() == 0)
        {
            System.out.println("Rectangle not found: " + commands[1]);
            return;
        }

        System.out.println("Rectangles found:");

        for (int i = 0; i < rectangles.size(); i++)
        {
            System.out.println(rectangles.get(i));
        }
    }

    /**
     * Produces a dump of the SkipList. Prints the contents of each node
     * starting with the head and shows their level, rectangles name, x, y, w, h
     * and the size of the Skip List.
     */
    public static void dump()
    {
        System.out.println("SkipList dump:");
        skipList.dump();
    }
}
