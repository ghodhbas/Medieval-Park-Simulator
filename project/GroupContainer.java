import java.util.*;
/**this class will hold the different groups of peope in the park
 */
public class GroupContainer
{
    private LinkedList<Group> container;

    /**
     * Constructor for objects of class GroupContainer
     */
    public GroupContainer()
    {container = new LinkedList<Group>();      
    }
    
    /** adds a group to the container
     * @param g - group to add to container
       */
    public void addGroup(Group g){
        container.add(g);
    }
    
    /**removes Group
     * @param i - the position of the group to be removed
       */
    public void removeGroup(int i){
        container.remove(i);
    }
    
    /** get a group from container at position i
     * @param i - the position of the group to get
       */
    public Group getGroup(int i ){
        return container.get(i);
    }
    
    /**return the size of the group container*/
    public int getSize(){
        return container.size();
    }
    
    /**clear the group Container */
    public void clear(){
        container.clear();
    }
}
