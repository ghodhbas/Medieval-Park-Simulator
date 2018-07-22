import java.util.*;
/**
 *This class is responsible for generating integer numbers with gaussian
 *normal destribution
 */
public class GaussianNumberGenerator
{
    private Random fRandom;
    
    /**constructor without seed*/
    public GaussianNumberGenerator()
    {
        fRandom = new Random();
    }
    
    /**constructor with seed
     * @param seed - seed for random generator
       */
    public GaussianNumberGenerator(long seed)
    {
        fRandom = new Random(seed);
    }

    /**generate a double random number from mean and standard deviation
     * @param mean - mean of groups arriving per time step
     * @param stdDev - standard Deviation of groups arriving per time step
       */
    public int generate(double mean, double stdDev){
        return (int) (mean +  fRandom.nextGaussian() * stdDev);
    }
    
}
