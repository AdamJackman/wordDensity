package readers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DensityReader {

		
		private static final String USER_AGENT = "Chrome/46.0.2490.71";
		protected String url_;
		protected Map<String, Integer> wordMap_;
		protected String [] HtmlIndicators_ = { "=", "<", ">", "/", "\"", "\\", "&", "$", "{", "}", ":", ";", "\t" };
		
		public DensityReader(){
			this("https://github.com/AdamJackman");			
		}		
		public DensityReader(String url){
			url_ = url;
			wordMap_ = new HashMap<String, Integer>();
		}
		
		/**
		 * Goes to the URL and will grab the Content
		 * @return BufferedReader - Contains page content
		 * @throws Exception	
		 */
		public BufferedReader sendGet() throws Exception {		
			//Change the url String into a URL
			URL obj = new URL(url_);
			//Create a Connection
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent", USER_AGENT);
			int responseCode = con.getResponseCode();
			if(responseCode == 200){
				BufferedReader response = new BufferedReader(
				        new InputStreamReader(con.getInputStream()));		
				return response;			
			} else {
				System.out.println("Error did not recieve response code 200");
				return null;
			}
			
			
		}
		
		/**
		 * Will take the Content line by line and search for the pattern
		 * @param in
		 * @return Will return the position of the first occurrence, otherwise -1 for not found
		 * @throws Exception
		 */
		public ArrayList<String> searchResponse(BufferedReader in) throws Exception {

			String inputLine;					
			String txt = "";
			//1. On each Line read relevant parts into the map
			while ((inputLine = in.readLine()) != null) {
				txt = inputLine.toLowerCase();
				countLine(txt);
			}
			in.close();
			//2. Sort the Map and place it in a List
			ArrayList<String> sortedList = sortMapToList();			
			return sortedList;
		}
		
		/**
		 * This will do some initial filtering and will add those that pass to
		 * the count map which will be  used to determine the most popular words
		 * @param line
		 */
		public void countLine(String line){
			String [] words = line.split(" ");
			for(int i=0; i<words.length; i++){
				boolean store = true;
				String curr = words[i];
				//Ignore blanks, ignore pure numerics
				if(curr.isEmpty() || curr.matches("-?(\\d)+((\\.\\d+)+)?")){
					continue;
				}
			
				//Filter HTML elements.				
				for(String indicator : HtmlIndicators_){
					if(curr.contains(indicator)){						
						store = false;
						break;
					}
				}
				//Increment the count if it exists or place 1 in the map
				if(store){
					Integer currCount = wordMap_.get(curr);				
					if(currCount != null){
						wordMap_.put(curr, ++currCount);
					} else {
						wordMap_.put(curr, 1);
					}	
				}								
			}
		}
		
		/*
		 * Sort the map into an ArrayList
		 */
		public ArrayList<String> sortMapToList(){
			List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<Map.Entry<String, Integer>>(wordMap_.entrySet());
			//Sort all file names in the target directory
			Collections.sort(sortedEntries, new Comparator<Map.Entry<String, Integer>>() {			
				//We are sorting by the calendar in the LogFile object
				public int compare(Map.Entry<String, Integer> m1, Map.Entry<String, Integer> m2) {
					return -(m1.getValue().compareTo(m2.getValue()));
				}
			});
			ArrayList<String> sortedWords = new ArrayList<String>();
			for(int i=0; i<sortedEntries.size(); i++){
				sortedWords.add(sortedEntries.get(i).getKey());
			}			
			return sortedWords;
		}

				

	
}
