package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class FirstWorkingTest {

	public static void main(String[] args) throws Exception {

		URL url = new URL("https://stream.twitter.com/1/statuses/filter.json");

		HttpURLConnection httpc = (HttpURLConnection) url.openConnection();

		String key = "Authorization";

		String value = "OAuth "
				+ "oauth_consumer_key=\"cCXiLMMDa2gamP8YaUQcA\", "
				+ "oauth_nonce=\"6646233af88377b936cc39aaedf8b074\", "
				+ "oauth_signature=\"%2Fuyh7Mg6Twq9q0YcUMFooe3Im%2Bw%3D\", "
				+ "oauth_signature_method=\"HMAC-SHA1\", "
				+ "oauth_timestamp=\"1341098398\", "
				+ "oauth_token=\"621835421-W4EoWQw0SL2jEaQnlne2dQltBZdNZIOVgs4Ak8oG\", "
				+ "oauth_version=\"1.0\"";

		httpc.addRequestProperty(key, value);

		httpc.setRequestMethod("POST");

		httpc.setDoOutput(true);

		// write

		OutputStreamWriter wr = new OutputStreamWriter(httpc.getOutputStream());

		wr.write("track=wsop");
		wr.flush();

		// read

		BufferedReader input = new BufferedReader(new InputStreamReader(
				httpc.getInputStream()));

		String inputLine = null;

		while ((inputLine = input.readLine()) != null)
			System.out.println(inputLine);

		input.close();

		httpc.disconnect();

		// XStream xstream = new XStream(new JettisonMappedXmlDriver());
		// xstream.alias("product", Product.class);
		// Product product = (Product)xstream.fromXML(json);
		// System.out.println(product.getName());

		// JSONTokener jsonTokener = new JSONTokener(new InputStreamReader(is,
		// "UTF-8"));
		//
		// while (true) {
		// try {
		// JSONObject jsonObject = new JSONObject(jsonTokener);
		// System.out.println("Got " + jsonObject);
		// } catch (JSONException ex) {
		// throw new IOException("Got JSONException: " + ex.getMessage());
		// }
		// }
	}
}
