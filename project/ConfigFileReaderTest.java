

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class ConfigFileReaderTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class ConfigFileReaderTest
{
    /**
     * Default constructor for test class ConfigFileReaderTest
     */
    public ConfigFileReaderTest()
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
    public void testread(){
        ConfigFileReader reader = new ConfigFileReader(1,"Config.txt");
        
        while(reader.hasNext()){
            reader.read();
        }
        
        for(int j = 0 ; j<reader.getRides().getSize();j++){
            System.out.println(reader.getRides().getRide(j));
            System.out.println();
        }
        
        System.out.println("arrival mean: "+ reader.getArrivalMean());
        System.out.println("arrival Std: "+ reader.getArrivalStd());
        System.out.println("indiv distrib: "+ reader.getIndivDistrib());
        System.out.println("friends distrib: "+ reader.getFriendsDistrib());
        System.out.println("family distrib: "+ reader.getFamilyDistrib());
        System.out.println("friends worrior rank range: "+ reader.getFriendsWorriorRankRange());
        System.out.println("friends knight rank range: "+ reader.getFriendsKnightsRankRange());
        System.out.println("friends royal rank range: "+ reader.getFriendsRoyalsRankRange());
        System.out.println("friends number mean: "+ reader.getNumberFriendsMean());
        System.out.println("friends number std: "+ reader.getNumberFriendsStd());
        System.out.println("Companion number mean: "+ reader.getNumberCompanionMean());
        System.out.println("Companion number std: "+ reader.getNumberCompanionStd());
        System.out.println("Companion rank mean: "+ reader.getRankCompanionMean());
        System.out.println("Companion rank std: "+ reader.getRankCompanionStd());
        System.out.println("Individual Worrior rank range: "+ reader.getIndivWorriorRange());
        System.out.println("Individual Knight rank range: "+ reader.getIndivKnightRange());        
        System.out.println("Individual Royal rank range: "+ reader.getIndivRoyalRange());
    }
    
}
