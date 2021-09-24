import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import student.TestCase;

/**
 * Class used to test the functionality of Rectangle1 the main class of this
 * program.
 * 
 * @author Ethan Neece (ethann)
 * @version 9/23/2021
 * 
 */
public class Rectangle1Test extends TestCase
{

    /**
     * setUp for Rectangle1 testing nothing to set up here.
     */
    public void setUp()
    {
        // Nothing to set up.
    }

    /**
     * Test the main method in Rectangle1.
     * 
     * @throws IOException
     */
    public void testMain() throws IOException
    {
        String[] input1 = {};

        Rectangle1.main(input1);
        assertEquals("Invoke with Rectangle1: filename\n",
                systemOut().getHistory());

        systemOut().clearHistory();

        String[] input2 = { "P1test1.txt"
        };
        Rectangle1.main(input2);

        String[] output = systemOut().getHistory().split("\n");

        Path path1 = Paths.get("P1test1Output.txt");
        List<String> correctOutput = Files.readAllLines(path1);

        for (int i = 0; i < correctOutput.size(); i++)
        {
            if (correctOutput.get(i).charAt(0) != 'N')
            {
                assertEquals(correctOutput.get(i), output[i]);
            }
        }
    }
}
