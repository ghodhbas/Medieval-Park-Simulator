

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class RandomNumberGeneratorTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class RandomNumberGeneratorTest
{
    /**
     * Default constructor for test class RandomNumberGeneratorTest
     */
    public RandomNumberGeneratorTest()
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
    public void testNbGen(){
        RandomNumberGenerator g = new RandomNumberGenerator();
        
        for(int i=0 ; i<100; i++){
        assertTrue((g.generateRank(10,12) >=10) && (g.generateRank(10,12)<= 12));
    }
    }
}
