

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class GroupTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class GroupTest
{
    /**
     * Default constructor for test class GroupTest
     */
    public GroupTest()
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
    public void testGroupConstructor(){
        Group g = new Group();
        assertTrue(g.getSize() == 0);
    }
    
    @Test
    public void testAddPerson(){
        Group g = new Group();
        
        //add first person
        g.addPerson(15);
        assertTrue(g.getSize() == 1);
        assertTrue(g.getRankRange() == 15);
       
        
        
        //add person new max rank
        g.addPerson(25);
        assertTrue(g.getSize() == 2);
        assertTrue(g.getRankRange() == 15);
       
        
        //add person new min rank
        g.addPerson(5);
        assertTrue(g.getSize() == 3);
        assertTrue(g.getRankRange() == 5);
       
        
    }
}
