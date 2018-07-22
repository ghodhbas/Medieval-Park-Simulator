
/**Generates groups
 */
public class GroupGenerator
{   
    
    private RandomNumberGenerator numGen ;
    private GaussianNumberGenerator gNumGen ;
    
    //people parameters
    private float indivDistrib, friendsDistrib, familyDistrib;
    private float indivWorriorRange, indivKnightRange,indivRoyalRange;
    private int numberCompanionMean, numberCompanionStd;
    private int rankCompanionMean, rankCompanionStd;
    private int numberFriendsMean, numberFriendsStd;
    private float friendsWorriorRankRange, friendsKnightsRankRange, friendsRoyalsRankRange;
    /**
     * Constructor for objects of class GroupGenerator
     * @param indivDistrib - individual distribution 
     * @param friendsDistrib - friends distribution 
     * @param familyDistrib - family distribution 
     * @param indivWorriorRange - individual worrior Range 
     * @param indivKnightRange - individual knight Range 
     * @param indivRoyalRange - individual royal Range 
     * @param numberCompanionMean - mean of number of companion
     * @param numberCompanionStd - standard deviation of number of companion
     * @param rankCompanionMean -  mean of the rank of companions
     * @param rankCompanionStd - standard deviation of the companion
     * @param numberFriendsMean - mean of the number of freinds
     * @param numberFriendsStd - standard deviation of the number of friends
     * @param friendsWorriorRankRange - rank range for the worriors in a group of friends
     * @param friendsKnightsRankRange - rank range for the knights in a group of friends
     * @param friendsRoyalsRankRange -rank range for the worriors in a group of friends
     * 
     */
    public GroupGenerator(float indivDistrib, float friendsDistrib, float familyDistrib ,float indivWorriorRange, float indivKnightRange, float indivRoyalRange, int numberCompanionMean, int numberCompanionStd, int rankCompanionMean, int rankCompanionStd, int numberFriendsMean, int numberFriendsStd, float friendsWorriorRankRange,float friendsKnightsRankRange,float friendsRoyalsRankRange)
    { numGen = new RandomNumberGenerator();
      gNumGen = new GaussianNumberGenerator();
      
      //save param
      this.indivDistrib = indivDistrib;
      this.indivWorriorRange= indivWorriorRange;
      this.indivKnightRange = indivKnightRange;
      this.indivRoyalRange = indivRoyalRange;
      this.friendsDistrib = friendsDistrib;
      this.familyDistrib = familyDistrib;
      this.numberCompanionMean = numberCompanionMean;
      this.numberCompanionStd = numberCompanionStd;
      this.rankCompanionMean = rankCompanionMean;
      this.rankCompanionStd = rankCompanionStd;
      this.numberFriendsMean = numberFriendsMean;
      this.numberFriendsStd = numberFriendsStd;
      this.friendsWorriorRankRange = friendsWorriorRankRange;
      this.friendsKnightsRankRange = friendsKnightsRankRange;
      this.friendsRoyalsRankRange = friendsRoyalsRankRange;
      
    }
    
    /**
     * Constructor for objects of class GroupGenerator
     * @param seed - seed for number generator
     * @param indivDistrib - individual distribution 
     * @param friendsDistrib - friends distribution 
     * @param familyDistrib - family distribution 
     * @param indivWorriorRange - individual worrior Range 
     * @param indivKnightRange - individual knight Range 
     * @param indivRoyalRange - individual royal Range 
     * @param numberCompanionMean - mean of number of companion
     * @param numberCompanionStd - standard deviation of number of companion
     * @param rankCompanionMean -  mean of the rank of companions
     * @param rankCompanionStd - standard deviation of the companion
     * @param numberFriendsMean - mean of the number of freinds
     * @param numberFriendsStd - standard deviation of the number of friends
     * @param friendsWorriorRankRange - rank range for the worriors in a group of friends
     * @param friendsKnightsRankRange - rank range for the knights in a group of friends
     * @param friendsRoyalsRankRange -rank range for the worriors in a group of friends
     * 
     */
    public GroupGenerator(long seed,float indivDistrib, float friendsDistrib, float familyDistrib ,float indivWorriorRange, float indivKnightRange, float indivRoyalRange, int numberCompanionMean, int numberCompanionStd, int rankCompanionMean, int rankCompanionStd, int numberFriendsMean, int numberFriendsStd, float friendsWorriorRankRange,float friendsKnightsRankRange,float friendsRoyalsRankRange){
    
      numGen = new RandomNumberGenerator(seed);
      gNumGen = new GaussianNumberGenerator(seed);
      
      //save param
      this.indivDistrib = indivDistrib;
      this.indivWorriorRange= indivWorriorRange;
      this.indivKnightRange = indivKnightRange;
      this.indivRoyalRange = indivRoyalRange;
      this.friendsDistrib = friendsDistrib;
      this.familyDistrib = familyDistrib;
      this.numberCompanionMean = numberCompanionMean;
      this.numberCompanionStd = numberCompanionStd;
      this.rankCompanionMean = rankCompanionMean;
      this.rankCompanionStd = rankCompanionStd;
      this.numberFriendsMean = numberFriendsMean;
      this.numberFriendsStd = numberFriendsStd;
      this.friendsWorriorRankRange = friendsWorriorRankRange;
      this.friendsKnightsRankRange = friendsKnightsRankRange;
      this.friendsRoyalsRankRange = friendsRoyalsRankRange;
    }
    
    
    /**generates a random group
     * @return the generated group
       */
    public Group generate(){
        float type = numGen.generateFloat();
        if(type <= indivDistrib){
            return generateIndividual();
        }else if( (type > indivDistrib) && (type <= friendsDistrib)){
            return generateFamily();
        }else{
            return generatePeers();
        }
    }
    
    /**generates an individual
     * @return the induvidual generated
       */
    public Individual generateIndividual(){
        float indivType = numGen.generateFloat();
        
        if (indivType <= indivWorriorRange){
            //generate worrior
            return  new Individual(numGen.generateRank(13,18));
        }else if ((indivType > indivWorriorRange)&&(indivType <=indivRoyalRange)){
            //generate knight
            return new Individual(numGen.generateRank(18,22));
        }else{
            //generate a Royalty
            return new Individual(numGen.generateRank(23,30));
        }
    }
    
    /** generates a family
     * @return the Family generated
       */
    public Family generateFamily(){
        Family fam = new Family();
        int compNb = (int) gNumGen.generate(numberCompanionMean,numberCompanionStd);
        //addcompanions to family
        for(int i=0; i<compNb;i++){
            fam.addPerson((int) gNumGen.generate(rankCompanionMean,rankCompanionStd));
        }
        return fam;
    }
    
    /** generates a peer group
     * @return the peers generated
       */
    public Peers generatePeers(){
        int groupSize = (int) gNumGen.generate(numberFriendsMean,numberFriendsStd);
        while(groupSize <= 1){
            groupSize = (int) gNumGen.generate(numberFriendsMean,numberFriendsStd);
        }
        Peers group = new Peers();        
        float groupType = numGen.generateFloat();
        if(groupType <=friendsWorriorRankRange){
            //group of worriors
            for(int i = 0; i < groupSize; i++){
                group.addPerson(numGen.generateRank(13,18));
            }
            return group;
        }else if ((groupType >friendsWorriorRankRange) && (groupType<=0.8)){
            //group of knights
            for(int i = 0; i < groupSize; i++){
                group.addPerson(numGen.generateRank(19,22));
            }
            return group;
        }else{
            //group of royals
            for(int i = 0; i < groupSize; i++){
                group.addPerson(numGen.generateRank(23,30));
            }
            return group;
        }
    }
}
