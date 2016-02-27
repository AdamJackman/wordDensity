package adamJackman.wordDensity;

import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App {
	
    public static void main( String[] args ){
    	
    	if(args.length < 1){
    		printUsage();
    		return;
    	}
    	
    	String url = args[1];
    	Integer count = 3;
    	if(args.length == 2){
    		count = Integer.parseInt(args[2]);
    	}        
        WordDensity wd = new WordDensity(url, count);
        ArrayList<String> res = wd.search();
        System.out.println(res.toString());
    }
    
    public static void printUsage(){
    	System.out.println("No");
    }
}
