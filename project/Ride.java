import java.util.*;
/**Class for the Rides
 */
public class Ride
{   
    private int maxRank, minRank, space, length, rideMaxCapacity, queueCapacity; //ride parameters
    private int injuryRate;
    private int rideCounter = 0; //will track the progress of a ride
    private int rideCurrentCapacity, queueCurrentCapacity; //current state
    private Integer key; //unique ride key;
    private String name;
    private ArrayList<Group> ride ;
    private LinkedList<Group> queue;
    RandomNumberGenerator gen ; //for injury generation
    /**
     * Constructor for objects of class Ride
     * @param seed - seed for the random generator
     * @param name - contains the name of the ride
     * @param minRnak - contains the maximum rank of the rid
     * @param space - the cost of the ride
     * @param length - the steps it takes to complete a ride
     * @param rideMaxCapacity - the maximum amout of people contained in the ride
     * @param queueCapacity - the maximum number of people in the queue of the ride
     * @param injuryRate - the percentage of being injured when riding the ride
     */
    public Ride(int seed, String name, int minRank, int maxRank, int space, int length, int rideMaxCapacity, int queueCapacity,int injuryRate )
    {
        //save variables
        this.name = name;
        this.minRank = minRank;
        this.maxRank = maxRank;
        this.space = space;
        this.length = length;
        this.rideMaxCapacity = rideMaxCapacity;
        this.queueCapacity = queueCapacity;
        this.injuryRate = injuryRate;
        
        this.queueCurrentCapacity = 0;
        this.rideCurrentCapacity =0;
        ride = new ArrayList<Group>();
        queue = new LinkedList<Group>();
        gen = new RandomNumberGenerator(seed);
    }
    
    /**Constructor for ride
      * @param seed - seed for the random generator
      * @param Ride - the ride to copy parameters from
        */
    public Ride(int seed, Ride r){
        this.name = r.getName();
        this.minRank = r.getMinRank();
        this.maxRank = r.getMaxRank();
        this.space = r.getSpace();
        this.length = r.getLength();
        this.rideMaxCapacity = r.getRideMaxCapacity();
        this.queueCapacity = r.getQueueCapacity();
        this.injuryRate = r.getInjuryRate();
        
        this.queueCurrentCapacity = 0;
        this.rideCurrentCapacity =0;
        ride = new ArrayList<Group>();
        queue = new LinkedList<Group>();
        
        gen = new RandomNumberGenerator(seed);
    }
    
    /** progress the ride
     * @param groupContainer - the current groups of people in the park but not in rides or queues
     * @return updated groupContainer after the state of ride is updated
     *       
       */
    public GroupContainer updateRideState(GroupContainer groupContainer){
        
        rideCounter++;
        //load passengers
        if(rideCounter % length == 1){
            return fillRide(groupContainer);
            
        }else if(rideCounter% length ==0){
            //unload passengers
            return dischargeGroups(groupContainer);
        }else{
            return groupContainer;
        }
    }
    
    /**fill ride with groups of people
     * @param groupContainer - the current groups of people in the park but not in rides or queues
     * @return updated groupContainer after the filling the ride
       */
    public GroupContainer fillRide(GroupContainer groupContainer){
        //start with the queue
        //the queue is not empty
        if(queue.size()>0){
            Group g= queue.peek();
            //add to ride till the queue is empty or the container is empty or the ride is full
            while((queue.size()>0) && (groupContainer.getSize()>0)&& (g.getSize()>0) && ((g.getSize()+this.rideCurrentCapacity) < rideMaxCapacity)){
                addToRide(dequeue());
                g= queue.peek();
            }
        }
        
        //if there's still space in ride and queue is empty fill from group container
        int i =0;
        if((rideCurrentCapacity < rideMaxCapacity) && (queue.size() ==0) && (groupContainer.getSize()>0)){
            //System.out.println("space in ride and queue empty"); for testing
            while((rideCurrentCapacity < rideMaxCapacity) && (groupContainer.getSize()>0) && (i<groupContainer.getSize())){
                //the group can go in this ride
                if((groupContainer.getGroup(i).getRankRange() <= this.maxRank) && (groupContainer.getGroup(i).getRankRange() >= this.minRank)){
                    //System.out.println("there's a group that can get in this ride");for testing
                    //there's enough space for the group
                    if((groupContainer.getGroup(i).getSize())<= (rideMaxCapacity - rideCurrentCapacity)){
                        //System.out.println("the group size is small enough to get in");for testing
                        //check if the group didnt do the ride before
                        if(!(groupContainer.getGroup(i).didRide(this))){
                            addToRide(groupContainer.getGroup(i));
                            groupContainer.removeGroup(i);
                            i--;
                        }
                    }
                }
                //get any other group who can fit in the ride
                i++;
            }
        }
        
        //apply injury on people on ride
        
               
        return groupContainer;
    }
    
    /**fill queue of ride from group container
     * @param groupContainer -  the current groups of people in the park but not in rides or queues
     * @return updated groupContainer after the filling the queue
       */
    public GroupContainer fillQueue(GroupContainer groupContainer){
        int i = 0 ;
        
        while((groupContainer.getSize()>0) && (queueCurrentCapacity < queueCapacity) && (i<groupContainer.getSize())){
                //check compatibitlity
                if((groupContainer.getGroup(i).getRankRange() <= this.maxRank) && (groupContainer.getGroup(i).getRankRange() >= this.minRank)){
                    //check if they fit
                    if((groupContainer.getGroup(i).getSize()+queueCurrentCapacity) <= queueCapacity){
                        //check if the group didn't do the ride before
                        if(!(groupContainer.getGroup(i).didRide(this))){
                            addToQueue(groupContainer.getGroup(i));
                            groupContainer.removeGroup(i);
                            i--;
                        }
                    }
                }
                //get any other group who can fit in
                i++;
        }
        
        return groupContainer;
    }
    
    
    /**add a group to ride
     * @param gr - the group to be added to the ride
       */
    public void addToRide(Group gr){
        //apply injury rate
        for(int i=0 ;i<gr.getSize();i++){
            if(gen.generateInjury() <= injuryRate){
                 gr.getPerson(i).injure();
            }
        }
        //save ride to done
        gr.addKey(this.key);
        //add group to ride
        ride.add(gr);
        this.rideCurrentCapacity = this.rideCurrentCapacity + gr.getSize();
    }
    
    /**add a group to queue
     * @param gr - group to be added to the queue
       */
    public void addToQueue(Group gr){
        queue.add(gr);
        this.queueCurrentCapacity = this.queueCurrentCapacity + gr.getSize();
    }
    
    /** dequeue and return first person in queue
     * @return return the first group in the queue
       */
    public Group dequeue(){
       queueCurrentCapacity--;
       return queue.poll();
    }
    
    /**discharge a group to groupContainer and clear the ride
     * @param container - the container of groups in the park
     * @return return the container with the groups discharged added to it
       */
    public GroupContainer dischargeGroups(GroupContainer container){
        for(int i =0; i<ride.size();i++){
            container.addGroup(ride.get(i));
        }
        ride.clear();
        this.rideCurrentCapacity = 0;
        return container;
    }
    
    /**increse wait time for people in queue*/
    public void increaseWaitTime(){
        for(int i=0 ; i<queue.size();i++){
            queue.get(i).increaseWaitTime();
        }
    }
    
    /**getter for minimum Rank*/
    public int getMinRank(){
        return minRank;
    }

    /**getter for maximum Rank*/
    public int getMaxRank(){
        return maxRank;
    }
    
    /**getter for max space*/
    public int getSpace(){
        return space;
    }
    
    /**getter for length of ride*/
    public int getLength(){
        return length;
    }
    
    /**getter for minimum Rank*/
    public int getRideMaxCapacity(){
        return rideMaxCapacity;
    }
    
    /**getter for minimum Rank*/
    public int getCurrentCapacity(){
        return rideCurrentCapacity;
    }
    
    /**getter for queue size*/
    public int getQueueCapacity(){
        return queueCapacity;
    }
    
     /**getter for queue size*/
    public int getQueueCurrentCapacity(){
        return queueCurrentCapacity;
    }
    
    /**getter for ride counter*/
    public int getRideCounter(){
        return rideCounter;
    }
    
    /**setter for ride counter*/
    public void setRideCounter(int i ){
        this.rideCounter = i;
    }
    
    /**sets the keyfor the ride*/
    public void setRideKey(Integer k){
        this.key = k;
    }
    /**getter foe ride key*/
    public Integer getRideKey(){
        return key;
    }
    /**getter for Injury rate*/
    public int getInjuryRate(){
        return injuryRate;
    }
    
    /**getter for name*/
    public String getName(){
        return name;
    }
    
    /**gets the queue*/
    public LinkedList<Group> getQueue(){
        return queue;
    }
    
    /**converts the infos of the ride to string*/
    public String toString(){
        return "name: "+ name +"\nMax Rank : "+ maxRank +"\nMin Rank: " + minRank +"\nSpace: "+ space +"\nLength: "+ length + "\nRide Max Capacity: "+ rideMaxCapacity +"\nQueue Max Capacity: "+ queueCapacity+ "\nCurrent people In Ride: "+rideCurrentCapacity+"\nCurrent people in Queue: "+ queueCurrentCapacity;
                
    }
}
