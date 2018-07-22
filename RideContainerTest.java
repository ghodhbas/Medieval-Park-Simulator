

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class RideContainerTest.
 *
 */
public class RideContainerTest
{
    /**
     * Default constructor for test class RideContainerTest
     */
    public RideContainerTest()
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
    public void testContainer(){
        RideContainer container = new RideContainer();
        assertTrue(container.getSize() == 0);
    }
    
    @Test
    public void testAddRide(){
        RideContainer container = new RideContainer();
        Ride ride = new Ride(1,"baka",0,15,200,30,100,200,10);
        container.addRide(ride);
        
        assertTrue(container.getSize() ==1);
        
        System.out.print(container.getRide(0).toString());
    }
}
