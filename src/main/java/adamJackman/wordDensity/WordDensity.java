package adamJackman.wordDensity;

import java.io.BufferedReader;
import java.util.ArrayList;

import readers.DensityReader;

public class WordDensity {
	
	private String url_;
	private int resultCount_;
	private int minLen_;
	
	public WordDensity(String url, int count){
		url_ = url;
		resultCount_ = count;
		minLen_ = 5;
	}
	
	public WordDensity(String url, int count, int min){
		url_ = url;
		resultCount_ = count;
		minLen_ = min;
	}

	/**
	 * Runs a search on the url given and returns an ArrayList of the best guess of the page's topics.
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
			if(response == null){
				System.out.println("Cannot receive get request on requested URL");
				return null;
			}
			//2.) Build the List of most common words
			ArrayList<String> commonWords = reader.searchResponse(response);
			//3.) Filter out words we do not want
			ArrayList<String> filteredWords = filterWords(commonWords);
			//4.) Return the result
			return filteredWords;
			
		} catch (Exception e) {
			System.out.println("Error: " + e);
		}
		return null;
	}
	
	/**
	 * Do filtering of the words and add them to a new ArrayList
	 * @param commonWords
	 * @return
	 */
	public ArrayList<String> filterWords(ArrayList<String> commonWords){
		ArrayList<String> filteredList = new ArrayList<String>();
		int count = 0;
		for (int i=0; i<commonWords.size(); i++){
			String curr = commonWords.get(i); 
			//Only grab as many words as requested
			if(count >= resultCount_){
				break;
			}
			//Filter out all words less than min len. small words are usually not good descriptors
			if(curr.length() < minLen_ || !( curr.matches("(.*?)([a-z]{"+minLen_+"})+(.*?)"))){
				continue;
			}			
			//Filter larger common HTML / JS words
			if(curr.contains("function") || curr.contains("typeof") || curr.contains("button") || curr.contains("header") || curr.contains("footer")){
				continue;
			}			
			filteredList.add(commonWords.get(i));
			count++;
		}
		return filteredList;
	}

}
