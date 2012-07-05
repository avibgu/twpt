package utils;

import org.junit.Test;

public class TweetsEmbedderTest {

	@Test
	public void testGetEmbedCode() {

		try {
			System.out.println(TweetsEmbedder.getEmbedCode("133640144317198338"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
