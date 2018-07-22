

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class FamilyTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class FamilyTest
{
    /**
     * Default constructor for test class FamilyTest
     */
    public FamilyTest()
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
    public void testFamilyConstructor(){
        Family f = new Family();
        assertTrue(f.getSize() == 2);
        assertTrue(f.rankRange == 25);
        
    }
    
    @Test
    public void testaddPerson(){
        //create family without companions
        Family f = new Family();
        
        //add companion
        f.addPerson(20);
        assertTrue(f.getSize() == 3);
        assertTrue(f.rankRange == 20);
        
               
        //add companion
        f.addPerson(12);
        assertTrue(f.getSize() ==4);
        assertTrue(f.rankRange == 12);
        
        
        //add companion
        f.addPerson(2);
        assertTrue(f.getSize() ==5);
        assertTrue(f.rankRange == 2);
        
        
    }
}
