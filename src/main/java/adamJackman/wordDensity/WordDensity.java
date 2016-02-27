package adamJackman.wordDensity;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import readers.DensityReader;

public class WordDensity {
	
	private String url_;
	
	public WordDensity(){}	
	public WordDensity(String url){
		url_ = url;
	}

	/*
	 * Runs A search on the page using the version initialized
	 * @return - Returns and array List of Strings most common on the page 
	 */
	public ArrayList<String> search(){

		//Check that initialized correctly
		if(url_.isEmpty()){
			System.out.println("Search Page is not initialized");
			return null;
		}
		
		try {
			//Process:
			//1.) Read the data from the URL using the searcher.
			DensityReader reader = new DensityReader(url_);
			BufferedReader response = reader.sendGet();
			//2.) Build the List of most common words
			ArrayList<String> commonWords = reader.searchResponse(response);
			//3.) Filter out words we do not want
			//4.) Return the result
			return commonWords;
			
		} catch (Exception e) {
			System.out.println("Error: " + e);
		}
		return null;
	}

}
