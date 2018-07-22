

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class GroupGeneratorTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class GroupGeneratorTest
{
    /**
     * Default constructor for test class GroupGeneratorTest
     */
    public GroupGeneratorTest()
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
    public void testGroupGenerator(){
        RideContainer rides = new RideContainer();
        rides.addRide( new Ride(1,"haha", 15,20,50,5,35,50,10));
        GroupGenerator generator = new GroupGenerator((float)0.2,(float)0.4,(float)1,(float)0.6,(float)0.8,(float)1,2,1,11,4,4,2, (float)0.4, (float)0.8,(float)1);
        for(int i =0 ; i<20 ;i++){
            System.out.println(generator.generate().toString());
        }
    }
    
   
}
