import java.util.*;
import java.io.*;
/**
 * Write a description of class ParamFileReader here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ParamFileReader
{
    private Scanner read ;
    private String previousLine = "a";
    private String name="";
    private ArrayList<String> rides= new ArrayList<String>();
    private int number = 0;
     
    /**
     * Constructor for objects of class ParamFileReader
     * @param paramFile - the name of the parameter file
     */
    public ParamFileReader(String paramFile)
    {
        try{
            read= new Scanner(new FileReader(paramFile));
        }catch(Exception e){
            System.out.println("error reading file");
        }
    }
    
    /** reads the parameter file*/
    public void read(){
        if( read.hasNextLine()){
            String line = read.nextLine();
            if(line.equals("ride")){
                readRideParameters(line);
            }
            for(int i =0 ;i<getNumber();i++){
                rides.add(name);
            }
            resetParameters();
        }
    }
    
    /** read the ride parameters
     * @param line - the line from which to read parameters
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
            
            //set ride occurence
            if(word.equals("number")){
                setRideOccurence(wordReader);
            }
        }
    }
    
    /**reset ride parameters */
    public void resetParameters(){
        name="";
        number=0;
    }
    
    /**set ride name
     * @param wordReader - the scanner to read the name
       */
    public void setRideName(Scanner wordReader){
        while(wordReader.hasNext()){
            name = name + wordReader.next() + " ";
        }
    }
    
    /** set ride occurence 
     * @param wordReader - the scanner to read the name
       */
    public void setRideOccurence(Scanner wordReader){
        while(wordReader.hasNext()){
            number = wordReader.nextInt();
        }
    }
    
    /** gets previous line of reader*/
    public String getPreviousLine(){
        return previousLine;
    }
    
    public int getNumber(){
        return number;
    }
    
    public ArrayList<String> getRides(){
        return rides;
    }
    
    /**check if the reader has a next token*/
    public boolean hasNext(){
        return read.hasNext();
    }
}
