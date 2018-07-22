

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class ParamFileReaderTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class ParamFileReaderTest
{
    /**
     * Default constructor for test class ParamFileReaderTest
     */
    public ParamFileReaderTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
    
    @Test
    public void testParamReader(){
        ParamFileReader reader = new ParamFileReader("Param.txt");
        while(reader.hasNext()){
            reader.read();
        }
        
        for(int i = 0 ;i< reader.getRides().size();i++){
            System.out.println(reader.getRides().get(i));
        }
    }
}
