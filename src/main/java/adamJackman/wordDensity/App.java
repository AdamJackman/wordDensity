package adamJackman.wordDensity;

import java.text.ParseException;
import java.util.ArrayList;

/**
 * Act as the jar command line interface with the user
 */
public class App {
	
    public static void main( String[] args ){
    	
    	if(args.length < 1){
    		printUsage();
    		return;
    	}
    	
    	String url = args[0];
    	Integer count = 3;
    	Integer minLen = 5;
    	try{
    		if(args.length > 1){
        		count = Integer.parseInt(args[1]);	
        	}        	
        	if(args.length > 2){    		
        		minLen = Integer.parseInt(args[2]);
        	}	
    	} catch (Exception e){
    		System.out.println("Invalid use params 2 and 3 should be integers");
    		printUsage();
    		return;
    	}
    	    	
    	System.out.println("Calling with: url: " + url + " and count: " + count);    	
        WordDensity wd = new WordDensity(url, count, minLen);        
        ArrayList<String> res = wd.search();
        if(res != null){
        	System.out.println("Result: " + res.toString());
        }        
    }
    
    public static void printUsage(){
    	System.out.println("--Usage");
    	System.out.println("java -jar thisJar url howManyResults minimumResultLength");
    	System.out.println("url -- This is the page that will be searched include");
    	System.out.println("howManyResults -- this determines how many words are returned");
    	System.out.println("minimumResultLength -- this determines how long words must be");
    	
    }
}
