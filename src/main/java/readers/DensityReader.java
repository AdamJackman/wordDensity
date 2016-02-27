package readers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DensityReader {

		
		private static final String USER_AGENT = "Chrome/46.0.2490.71";
		protected String url_;
		
		public DensityReader(){
			url_ = "https://github.com/AdamJackman";
		}
		
		public DensityReader(String url){
			url_ = url;
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
	 
			BufferedReader response = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));		
			return response;		
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
			
			while ((inputLine = in.readLine()) != null) {
				//Search each line each line
				txt = inputLine.toLowerCase();
		    //    if (offset < txt.length()){
		        	System.out.println("Match found");
		      //  	found = true;
		        	break;
		       // }
			}
			in.close();
			
			
			return null;
		}			
					

	
}
