

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**Test Class for GroupContainer
 */
public class GroupContainerTest
{
    /**
     * Default constructor for test class GroupContainerTest
     */
    public GroupContainerTest()
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
    {}
    
    /** tests constructor */
    @Test
    public void testConstructor(){
        GroupContainer container = new GroupContainer();
        assertTrue(container.getSize() == 0);
    }
    
    /** tests add group method*/
    @Test
    public void testAddGroup(){
        GroupContainer container = new GroupContainer();
        
        GroupGenerator g = new GroupGenerator((float)0.2,(float)0.4,(float)1,(float)0.6,(float)0.8,(float)1,2,1,11,4,4,2, (float)0.4, (float)0.8,(float)1);
        Group gr = g.generate();
        container.addGroup(gr);
        
        assertTrue( container.getSize() == 1);
        assertTrue(container.getGroup(0) == gr);
    }
    
    
}
