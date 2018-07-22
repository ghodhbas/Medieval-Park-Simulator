import java.util.ArrayList;
/**
 *Class container containing rides
 */
public class RideContainer
{
    ArrayList<Ride> container;
    /** Constructor for objects of class RideContainer*/
    public RideContainer()
    {container = new ArrayList<Ride>();
    }

    
    /**add ride to container
     * @param ride - ride to be added to the container
       */
    public void addRide(Ride ride){
        container.add(ride);
    }
    
    /**getter for ride
     * @param i - the position of the ride int the container
     * @return the ride in the position i
       */
    public Ride getRide(int i){
        return container.get(i);
    }
    
    /**getter for size
     * @return the size of the container
       */
    public int getSize(){
        return container.size();
    }
}
