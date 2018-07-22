
/**
 *representing a Person constituting the family, peers, or individual gorup
 */
public class Person
{
    int rank;
    boolean injured =false;
    
    /**
     * Constructor for objects of class Person
     * @param rank - rank of a person
     */
    public Person(int rank)
    { 
        this.rank = rank;
    }

    /** rank getter*/
    public int getRank(){
        return rank;
    }
    
    /**injure the person*/
    public void injure(){
        injured = true;
    }
    
    /**get if the person is injured*/
    public boolean isInjured(){
        return injured;
    }
}
