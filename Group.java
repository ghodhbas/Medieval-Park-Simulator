import java.util.*;
/**
 * Group class which contain all the details about groups in the park
 */
public class Group
{
    protected int rankRange; //will determin which ride are accessible
    protected Integer waitTime = 0;//track if a person is in queue
    protected ArrayList<Person> group ;
    protected ArrayList<Integer> ridesDone = new ArrayList<Integer>(); //containes key of rides done
    /** Constructor for objects of class Group*/
    public Group()
    {  group = new ArrayList<Person>();
       RideContainer rides = new RideContainer();
    } 
    
    /** returns the whole group */
    public ArrayList<Person> getGroup(){
        return group;
    }
    
    /**add a person to the group
     * @param rank - rank of person to be added
       */
    public void addPerson( int rank){
        Person p = new Person(rank);
        group.add(p);
        updateRank();
    }
    
    /** update rank*/
    protected void updateRank(){
        rankRange = getPerson(0).getRank();
        for(int i=0; i< getSize();i++){
            //the  member with the lowest rank decide which rides are possible
            if(getPerson(i).getRank()< getRankRange()){
                rankRange = getPerson(i).getRank();
            }
        }
    }
    
    /**checks if the group didthe ride before
     * @param r - ride to be checked
     * @return true if already went through this ride
       */
    public boolean didRide(Ride r){
        return ridesDone.contains(r.getRideKey());
    }
    
    /**get person in position i
     * @param i - the position of the person to get in the group
     * @return return the person in position i
       */
    public Person getPerson(int i){
        return group.get(i);        
    }
    
    /**add the ride done
     * @param key - unique key of the ride
       */
    public void addKey(Integer key){
        ridesDone.add(key);
    }
    
    /**maxRank getter*/
    public int getRankRange(){
        return rankRange;
    }
    
    /**size getter*/
    public int getSize(){
        return group.size();
    }
    
    /**set group in queue mode*/
    public void increaseWaitTime(){
     waitTime++;
    }
    
    /**get wait time*/
    public Integer getWaitTime(){
        return waitTime;
    }
    
    /** convert group to string */
    public String toString(){
        String s ="";
        s= s + "size = " + getSize() + "\n";
        for(int i=0 ; i< getSize(); i++){
            s= s + "rank of member " + i + ": " + getPerson(i).getRank() +"\n";
        }
        s = s + "Rank range: " + rankRange + "\n \n";
        return s;
    }
  
}
