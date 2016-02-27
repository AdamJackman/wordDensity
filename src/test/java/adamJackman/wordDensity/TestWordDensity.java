package adamJackman.wordDensity;

import org.junit.Test;

import junit.framework.TestCase;

public class TestWordDensity extends TestCase {

	@Test
	public void testRunWordDensity(){
		WordDensity wd = new WordDensity("https://www.github.com/AdamJackman", 3);
		try{
			System.out.println(wd.search().toString());
		}
		catch (Exception e){
			fail("Error: " + e);
		}
	}
	
	@Test
	public void testTheToaster(){
		WordDensity wd = new WordDensity("http://www.amazon.com/Cuisinart-CPT-122-Compact-2-Slice-Toaster/dp/B009GQ034C/ref=sr_1_1?s=kitchen&ie=UTF8&qid=1431620315&sr=1-1&keywords=toaster", 5);
		try{
			System.out.println(wd.search());
		}
		catch (Exception e){
			fail("Error: " + e);
		}
	}
	
}
