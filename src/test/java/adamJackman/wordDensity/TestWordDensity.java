package adamJackman.wordDensity;

import org.junit.Test;

import junit.framework.TestCase;

public class TestWordDensity extends TestCase {

	@Test
	public void testRunWordDensity(){
		WordDensity wd = new WordDensity("https://github.com/AdamJackman", 5);
		try{
			wd.search();
		}
		catch (Exception e){
			fail("Error: " + e);
		}
	}
	
	@Test
	public void testTheToaster(){
		WordDensity wd = new WordDensity("http://www.amazon.com/Cuisinart-CPT-122-Compact-2-Slice-Toaster/dp/B009GQ034C/ref=sr_1_1?s=kitchen&ie=UTF8&qid=1431620315&sr=1-1&keywords=toaster", 5);
		try{
			wd.search();
		}
		catch (Exception e){
			fail("Error: " + e);
		}
	}
	
}
