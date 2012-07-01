package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.*;
import org.apache.commons.httpclient.params.HttpMethodParams;

public class ApacheTest {

	public static void main(String[] args) {

		// Create an instance of HttpClient.
		HttpClient client = new HttpClient();

		// Create a method instance.
		PostMethod method = new PostMethod(
				"https://stream.twitter.com/1/statuses/filter.json");

		// Provide custom retry handler is necessary
		method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler(3, false));

		String key = "Authorization";

		String value = "OAuth " +
				"oauth_consumer_key=\"cCXiLMMDa2gamP8YaUQcA\", " +
				"oauth_nonce=\"6646233af88377b936cc39aaedf8b074\", " +
				"oauth_signature=\"%2Fuyh7Mg6Twq9q0YcUMFooe3Im%2Bw%3D\", " +
				"oauth_signature_method=\"HMAC-SHA1\", " +
				"oauth_timestamp=\"1341098398\", " +
				"oauth_token=\"621835421-W4EoWQw0SL2jEaQnlne2dQltBZdNZIOVgs4Ak8oG\", " +
				"oauth_version=\"1.0\"";

		method.getParams().setParameter(key, value);

		NameValuePair[] data = { new NameValuePair("track", "wsop") };

		method.setRequestBody(data);

		try {

			// Execute the method.
			int statusCode = client.executeMethod(method);

			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: " + method.getStatusLine());
			}

			// Read the response body.
			byte[] responseBody = method.getResponseBody();

			// Deal with the response.
			// Use caution: ensure correct character encoding and is not binary
			// data
			System.out.println(new String(responseBody));

			BufferedReader input = new BufferedReader(new InputStreamReader(
					method.getResponseBodyAsStream()));

			String inputLine = null;

			while ((inputLine = input.readLine()) != null)
				System.out.println(inputLine);

			input.close();
		}

		catch (HttpException e) {

			System.err.println("Fatal protocol violation: " + e.getMessage());
			e.printStackTrace();
		}

		catch (IOException e) {

			System.err.println("Fatal transport error: " + e.getMessage());
			e.printStackTrace();
		}

		finally {

			// Release the connection.
			method.releaseConnection();
		}
	}
}
