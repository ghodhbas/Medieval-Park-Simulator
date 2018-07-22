import java.util.*;
import java.io.*;
/**
Reads config file and return either a ride or settings
 */
public class ConfigFileReader
{   
    //ride variables
    private String previousLine = "a";
    private Scanner read ;
    private int space = 0, length=0, capacity=0, queue=0;
    private int injury =0;
    private int rideMaxRank , rideMinRank;
    private int parkSize= 0;
    private String name="";
    private ArrayList<String> rideGroups = new ArrayList<String>();
    
    //people variables
    private int arrivalMean, arrivalStd;
    private float indivDistrib, friendsDistrib, familyDistrib;
    private float friendsWorriorRankRange, friendsKnightsRankRange, friendsRoyalsRankRange;
    private int numberFriendsMean, numberFriendsStd;
    private int numberCompanionMean, numberCompanionStd;
    private int rankCompanionMean, rankCompanionStd;
    private float indivWorriorRange, indivKnightRange,indivRoyalRange;
    private int seed;
    private RideContainer rides= new RideContainer();
    
    /**
     * Constructor for objects of class ConfigFileReader
     * @param seed - seed for the random generator
     * @param configFile - the name of the configuration file
     */
    public ConfigFileReader(int seed, String configFile)
    {  
        try{
            read= new Scanner(new FileReader(configFile));
        }catch(Exception e){
            System.out.println("error reading file");
        }
    }
    
    /** read next bloc in the file
     * 
       */
    public void read(){
        if( read.hasNextLine()){
            String line = read.nextLine();
            if(line.equals("ride")){
                readRideParameters(line);
                rides.addRide(new Ride(seed, name,rideMinRank,rideMaxRank,space,length, capacity,queue,injury));
                resetRideParameters();
            }
            
            if(line.equals("people")){
                readPeopleParameters(line);
            }
            
        }
    }
    
    /** read parameters according to keywords 
     * @param line - the to read the parameters from
       */
    public void readRideParameters(String line){
        //read parameters of ride
        while(read.hasNextLine()){            
            line = read.nextLine();

            if(line.equals("")){
                previousLine = line;
                break;
            }

            Scanner wordReader = new Scanner(line);
            String word = wordReader.next();
            //set ride name
            if(word.equals("type")){
                setRideName(wordReader);
            }
            
            //set ride accessible groups
            if(word.equals("group")){
                setRideGroups(wordReader);
                setRideMaxMinRank(rideGroups);
            }
            
            //sets ride space
            if(word.equals("space")){
                setRideSpace(wordReader);
            }
            
            //sets ride length
            if(word.equals("length")){
                setRideLength(wordReader);
            }
            
            //sets ride capacity
            if(word.equals("capacity")){
                setRideCapacity(wordReader);
               
            }
            
            //sets ride queue
            if(word.equals("queue")){
                setRideQueue(wordReader);
            }
            
            //sets ride injury rate
            if(word.equals("injury")){
                setRideInjury(wordReader);
            }
        }
        
    }
    
    /**get the parameters of the people
     * @param line - read parameters from it
       */
    public void readPeopleParameters(String line){
        //read parameters of people
        while(read.hasNextLine()){   
            line = read.nextLine();
            
            if(line.equals("")){
                previousLine = line;
                break;
            }
            
            
            Scanner wordReader = new Scanner(line);
            String word = wordReader.next();
            //set arrival settings
            if(word.equals("arrival")){
                setArrivalConfig(wordReader);
            }
            
            //set group distribution settings
            if(word.equals("groupDistribution")){
                setGroupDistribution(wordReader);
            }
            
            //set rank distrib of group of peers
            if(word.equals("friendRank")){
                setFriendsRank(wordReader);
            }
            
            //set distrib of number of friends
            if(word.equals("numberFriend")){
                setFriendsNumber(wordReader);
            }
            
            //set distrib of number of friends
            if(word.equals("numberComp")){
                setCompanionNumber(wordReader);
            }
            
            //set rank of companion distribution
            if(word.equals("rankComp")){
                setCompanionRank(wordReader);
            }
            
            //set rank of companion distribution
            if(word.equals("individual")){
                setIndividual(wordReader);
            }
        }
    }
    
    /**reset ride parameters */
    public void resetRideParameters(){
        space = 0;
        length=0;
        capacity=0;
        queue=0;
        injury =0;
        rideMaxRank=0;
        rideMinRank=0;
        name="";
        rideGroups = new ArrayList<String>();
    }
    
    
    /**********************Setters for rides************************/
    
    /**get ride name*/
    public void setRideName(Scanner wordReader){
        while(wordReader.hasNext()){
            name = name + wordReader.next() + " ";
        }
    }
    
    /**creates an array list of ride groups*/
    public void setRideGroups(Scanner wordReader){
        String blockOfGroups="";
        while(wordReader.hasNext()){
             blockOfGroups = wordReader.next();
        }
       
        Scanner groupDevider = new Scanner(blockOfGroups);
        
        groupDevider.useDelimiter(",");
        while(groupDevider.hasNext()){
            rideGroups.add(groupDevider.next());
        } 
               
    }
    
    /**sets max and min rank of ride*/
    public void setRideMaxMinRank(ArrayList<String> rideGroups){
        //set max rank
        String maxRank = rideGroups.get(0);
        if(maxRank.equals("royals")){
            rideMaxRank=30;
        }else if(maxRank.equals("knights")){
            rideMaxRank=22;
        }else if(maxRank.equals("worriors")){
            rideMaxRank=18;
        }else if(maxRank.equals("servants")){
            rideMaxRank=12;
        }else if(maxRank.equals("peasants")){
            rideMaxRank=4;
        }
        
        //set min rank
        String minRank = rideGroups.get(rideGroups.size()-1);
        if(minRank.equals("roayls")){
            rideMinRank=23;
        }else if(minRank.equals("knights")){
            rideMinRank=19;
        }else if(minRank.equals("worriors")){
            rideMinRank=13;
        }else if(minRank.equals("servants")){
            rideMinRank=5;
        }else if(minRank.equals("peasants")){
            rideMinRank=0;
        }
    }
    
    /**sets the space of a ride*/
    public void setRideSpace(Scanner wordReader){
        while(wordReader.hasNext()){
            space = wordReader.nextInt();
        }
    }
    
    /**sets the length of a ride*/
    public void setRideLength(Scanner wordReader){
        while(wordReader.hasNext()){
            length = wordReader.nextInt();
        }
    }
    
    /**sets the capacity of a ride*/
    public void setRideCapacity(Scanner wordReader){
        while(wordReader.hasNext()){
            capacity = wordReader.nextInt();
        }
    }
    
    /**sets the queue of a ride*/
    public void setRideQueue(Scanner wordReader){
        while(wordReader.hasNext()){
            queue = wordReader.nextInt();
        }
    }
    
    /**sets the injury rate of the ride */ 
    public void setRideInjury(Scanner wordReader){
        while(wordReader.hasNext()){
            injury = wordReader.nextInt();
        }
    }
    
    
    
    /***************setters for people************************/
    
    /**set arrival setting*/
    public void setArrivalConfig(Scanner wordReader){
        while(wordReader.hasNext()){
            arrivalMean = wordReader.nextInt();
            arrivalStd = wordReader.nextInt();
        }        
    }
    
    public void setGroupDistribution(Scanner wordReader){
        while(wordReader.hasNext()){
            indivDistrib =  wordReader.nextFloat();
            friendsDistrib = wordReader.nextFloat();
            familyDistrib = wordReader.nextFloat();
        }
    }
    
    /** sets firends rnak distribution*/
    public void setFriendsRank(Scanner wordReader){
        while(wordReader.hasNext()){
           friendsWorriorRankRange = wordReader.nextFloat();
           friendsKnightsRankRange = wordReader.nextFloat();
           friendsRoyalsRankRange = wordReader.nextFloat();
        }
    }   
    
    /** sets firends rank distribution*/
    public void setFriendsNumber(Scanner wordReader){
        while(wordReader.hasNext()){
           numberFriendsMean= wordReader.nextInt();
           numberFriendsStd = wordReader.nextInt();
        }
    }   
    
    /** sets companion number distribution */
    public void setCompanionNumber(Scanner wordReader){
        while(wordReader.hasNext()){
           numberCompanionMean= wordReader.nextInt();
           numberCompanionStd = wordReader.nextInt();
        }
    }   
    
    /** sets companion rank distribution */
    public void setCompanionRank(Scanner wordReader){
        while(wordReader.hasNext()){
           rankCompanionMean= wordReader.nextInt();
           rankCompanionStd = wordReader.nextInt();
        }
    }   
    
    /** sets individual  distribution */
    public void setIndividual(Scanner wordReader){
        while(wordReader.hasNext()){
           indivWorriorRange= wordReader.nextFloat();
           indivKnightRange = wordReader.nextFloat();
           indivRoyalRange = wordReader.nextFloat();
        }
    }   
    
    /** gets previous line of reader*/
    public String getPreviousLine(){
        return previousLine;
    }

    /**check if the reader has a next token*/
    public boolean hasNext(){
        return read.hasNext();
    }

    
    
    
    
    /*******************getters for test purposes ********************************/
    
    /**gets the name of the ride*/
    public String getName(){
        return name;
    }
    
    /**gets ride groups*/
    public ArrayList<String> getRideGroups(){
        return rideGroups;
    }
    
    /**get the space of a ride*/
    public int getSpace(){
        return space;
    }
    
    /**get the space of a ride*/
    public int getLength(){
        return length;
    }
    
    /**get the space of a ride*/
    public int getCapacity(){
        return capacity;
    }
    
    /**get the space of a ride*/
    public int getQueue(){
        return queue;
    }
    
    /**get the injury rate of a ride*/
    public int getInjury(){
        return injury;
    }
    
    /** get the max rank of the ride*/
    public int getRideMaxRank(){
        return rideMaxRank;
    }
    
    /** get the min rank of the ride*/
    public int getRideMinRank(){
        return rideMinRank;
    }
    
    /**returns a ride*/
    public RideContainer getRides(){
        return rides;
    }
    
    /**returs a arrival mean rate*/
    public int getArrivalMean(){
        return arrivalMean;
    }
    
    /**returs a arrival rate std*/
    public int getArrivalStd(){
        return arrivalStd;
    }
    
    /**return individual group distribution*/
    public float getIndivDistrib(){
        return indivDistrib;
    }
    
    /**return individual group distribution*/
    public float getFriendsDistrib(){
        return friendsDistrib;
    }
    
    /**return individual group distribution*/
    public float getFamilyDistrib(){
        return familyDistrib;
    }
    
    /**returns friend worriors Rank range*/
    public float getFriendsWorriorRankRange(){
        return friendsWorriorRankRange;
    }
    
    /**returns friend knights Rank range*/
    public float getFriendsKnightsRankRange(){
        return friendsKnightsRankRange;
    }
    
    /**return friend Rank std*/
    public float getFriendsRoyalsRankRange(){
        return friendsRoyalsRankRange;
    }
    
    /**return friend number mean*/
    public int getNumberFriendsMean(){
        return numberFriendsMean;
    }
    
    /**return friend number std*/
    public int getNumberFriendsStd(){
        return numberFriendsStd;
    }
    
    
    /**return companion number mean*/
    public int getNumberCompanionMean(){
        return numberCompanionMean;
    }
    
    /**return companion number std*/
    public int getNumberCompanionStd(){
        return numberCompanionStd;
    }
    
    /**return companion rank mean*/
    public int getRankCompanionMean(){
        return rankCompanionMean;
    }
    
    /**return compagnion Rank std*/
    public int getRankCompanionStd(){
        return rankCompanionStd;
    }
    
     /**return individual worrior range*/
    public float getIndivWorriorRange(){
        return indivWorriorRange;
    }
    
    /**return individual knight range*/
    public float getIndivKnightRange(){
        return indivKnightRange;
    }
    
    /**return individual knight range*/
    public float getIndivRoyalRange(){
        return indivRoyalRange;
    }
}
