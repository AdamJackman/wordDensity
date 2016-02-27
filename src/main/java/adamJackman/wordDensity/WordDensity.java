package adamJackman.wordDensity;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import readers.DensityReader;

public class WordDensity {
	
	private String url_;
	private int resultCount_;
	
	public WordDensity(String url, int count){
		url_ = url;
		resultCount_ = count;
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
			ArrayList<String> filteredWords = filterWords(commonWords);
			//4.) Return the result
			return filteredWords;
			
		} catch (Exception e) {
			System.out.println("Error: " + e);
		}
		return null;
	}
	
	public ArrayList<String> filterWords(ArrayList<String> commonWords){
		ArrayList<String> filteredList = new ArrayList<String>();
		int count = 0;
		for (int i=0; i<commonWords.size(); i++){
			//Only grab as many words as requested
			if(count >= resultCount_){
				break;
			}
			//Filter out all words less than 5, any word shorter than 5 characters is deemed an unprecise description
			if(commonWords.get(i).length() < 5){
				continue;
			}
			//Filter larger common HTML words
			if(commonWords.contains("function") || commonWords.contains("typeof") || commonWords.contains("button") || commonWords.contains("header") || commonWords.contains("footer")){
				continue;
			}
			filteredList.add(commonWords.get(i));
			count++;
		}
		return filteredList;
	}

}
