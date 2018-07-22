import java.io.*;
/**
 *This class is responsible of reading and initializing data and passing it to the Park class
 *This class is the controller / commander of the program. 
 *It reads the name of the Configuration and Parameter files (uses ConfigFileReader.java and ParamFileReader.java to deconstruct them and saves the data),
 *it initialize the park, and runs the simulation while writing the output in 3 different output files. 
 *This class controls how the simulation will run and gets the inputs and outputs.
 */
public class Simulation
{   
    /**main method that runs the program*/
    public static void main(String[] args){
        String configFile = args[0];
        String paramFile = args[1];
        int seed = 10;
        
        //read and save config
        ConfigFileReader reader = new ConfigFileReader(seed,configFile);
        while(reader.hasNext()){
            reader.read();
        }
        
        //read and save parameters
        ParamFileReader paraReader = new ParamFileReader(paramFile);
        while(paraReader.hasNext()){
            paraReader.read();
        }        
               
        Park park = new Park(seed, reader, paraReader);       
        
        float avgWait =0;
        float injuryRatio = 0;
        try{
            PrintWriter writer = new PrintWriter("output3.txt");
            //simulate
            park.init();
            park.run(seed);
            
            avgWait = park.getAvgWaitTime();                   
            injuryRatio = 100 * park.getInjuryRatio();                  
                
            writer.println("Average total wait: "+avgWait +" steps");
            writer.println("total percentage of injured people " + injuryRatio);
            writer.close();
        }catch(Exception e){
            System.out.println("error writing output 3");
        }
    }
    
    
    
}
