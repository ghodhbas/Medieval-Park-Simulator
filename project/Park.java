import java.io.*;
/**the class that will controles the park
 */
public class Park
{   
    ConfigFileReader config;
    ParamFileReader param;
    
    RideContainer rides = new RideContainer();
    GroupContainer groupContainer = new GroupContainer();
    int injuryCounter=0; //track the number of people who got injured
    int peopleCounter=0;//track the total number of people who visited the park
    int groupCounter =0;
    int totalWaitTime =0;
    int seed;
    /** Constructor for objects of class Park
     * @param seed - seed for the random generator
     * @param config - contains the information from config file
     * @param param - contains the parameters of the simulation
       */
    public Park(int seed, ConfigFileReader config, ParamFileReader param)
    {   this.config = config;
        this.param = param;
        this.seed = seed;
    }
    
    /**intitialize the park*/
    public void init(){
        int key = 0;
        //initialize the rides of the park
        for(int i=0 ; i< param.getRides().size();i++){
            String name = param.getRides().get(i);
            int j =0;
            //get the position of the correctride to add in config
            while((j< config.getRides().getSize()) &&(config.getRides().getRide(j).getName().compareTo(name) != 0 ) ){
                j++;
            }
            Ride r = new Ride(seed, config.getRides().getRide(j));
            r.setRideKey(key);
            key++;
            rides.addRide(r);
        }
    }
    
    /** run the simulation */
    public void run(int seed){
        //initialize group generator
        GroupGenerator groupGen = new GroupGenerator(seed, config.getIndivDistrib(), config.getFriendsDistrib(), config.getFamilyDistrib(),config.getIndivWorriorRange(), config.getIndivKnightRange(), config.getIndivRoyalRange(),config.getNumberCompanionMean(), config.getNumberCompanionStd(), config.getRankCompanionMean(),config.getRankCompanionStd(), config.getNumberFriendsMean(), config.getNumberFriendsStd(), config.getFriendsWorriorRankRange(), config.getFriendsKnightsRankRange(), config.getFriendsRoyalsRankRange());      
        GaussianNumberGenerator gGen = new GaussianNumberGenerator(seed);
        //intialize people parameters
        injuryCounter=0; //track the number of people who got injured
        peopleCounter=0;//track the total number of people who visited the park
        groupCounter =0;
        totalWaitTime =0;
        try{
            PrintWriter writer1 = new PrintWriter("output1.csv");
            PrintWriter writer2 = new PrintWriter("output2.csv");
            writer1.println("Average wait time:");
            writer2.println("ratio of injured people: , Number Of injured people, Number Of All people ");
            for( int i =0; i<1000;i++){
                //Group of people arrive
                int arrivalNumber = gGen.generate(config.getArrivalMean(),config.getArrivalStd());
                for(int j = 1 ; j<= arrivalNumber; j++){
                    Group g = groupGen.generate();
                    groupCounter++;
                    peopleCounter = peopleCounter + g.getSize();
                    groupContainer.addGroup(g);
                }
                
                
                //update ride status
                for(int k =0;k<rides.getSize();k++){
                    //update state of rides and groupContainer
                    groupContainer = rides.getRide(k).updateRideState(groupContainer);
                }
                
                //fill queue
                for(int l=0 ;l<rides.getSize();l++){
                    //fill queues of rides
                    groupContainer = rides.getRide(l).fillQueue(groupContainer);
                }
                
                //increase wait time for people in queue
                for(int p=0 ;p<rides.getSize();p++){
                    rides.getRide(p).increaseWaitTime();
                }
                
                //get the number of injured people that are leaving
                for(int m=0; m<groupContainer.getSize();m++){
                    for(int n =0; n<groupContainer.getGroup(m).getSize();n++){
                        if(groupContainer.getGroup(m).getPerson(n).isInjured()){
                            injuryCounter++;
                        }
                    }
                }
                
                //increase total wait time
                for(int o=0;  o<groupContainer.getSize();o++){
                    totalWaitTime  = totalWaitTime + groupContainer.getGroup(o).getWaitTime();
                }
                
                //clear group container= groups cant's find space and leave
                groupContainer.clear();
                
                
                
                if(i%10==0){
                    //writing data every 10 steps
                    writer1.println(((float) totalWaitTime / groupCounter)   );
                    writer2.println((100*(float) injuryCounter / peopleCounter) + "," +injuryCounter +","+peopleCounter);
                }
            }
            
            writer1.close();
            writer2.close();
            
            /**
            //for testing purposes
            System.out.println("number of groups left in container: " + groupContainer.getSize());
            System.out.println();
            System.out.println("number of rides: "+rides.getSize());    
            System.out.println();
            for(int k =0;k<rides.getSize();k++){
                System.out.println(rides.getRide(k).toString());
                System.out.println();
            }
            
            System.out.println("total people visiting: " + peopleCounter);
            System.out.println("people Injured: " + injuryCounter);
            System.out.println("total Wait Time: " + totalWaitTime);
            System.out.println("Average wait Time per group: " + ((float) totalWaitTime / groupCounter));
            */
           
        }catch(Exception e){
                System.out.println("error writing output");
        }
    }
    
    /**get rides in park
     * @return the injury ratio in the park
       */
    public float getInjuryRatio(){
        return ((float) injuryCounter / peopleCounter);
    }
    
    /**get average wait Time
     * @return the average wait time in the park
       */
    public float getAvgWaitTime(){
        return ((float) totalWaitTime / groupCounter);
    }
}
