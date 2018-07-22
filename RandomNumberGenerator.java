import java.util.*;
/**
 * class responsible for generating a random float between 0 and 1
 */
public class RandomNumberGenerator
{
    private Random fRandom;
    
    /**constructor without seed*/
    public RandomNumberGenerator()
    { fRandom = new Random();
    }
    
    /**constructor with seed
     * @param seed - seed for the random generator
       */
    public RandomNumberGenerator(long seed)
    {fRandom = new Random(seed);
    }
    
    /**generate a random float between 0 and 1
     * @return random float generated
       */
    public float generateFloat(){
        return  fRandom.nextFloat();
    }
    
    /**generate a rank within  the range
     * @param minRank - minimum rank to be genrated
     * @param maxRank - maximum rank to be generated
     * @return random rank generated
       */
    public int generateRank(int minRank, int maxRank){
        return fRandom.nextInt(maxRank - minRank +1) + minRank;
    }
    
    /**generates an injury roll between 1-100
     * @return generate possibility to be injured
       */
    public int generateInjury(){
        return fRandom.nextInt(100)+1;
    }
}
