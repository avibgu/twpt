package main;

import java.io.IOException;
import java.io.InputStreamReader;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.scribe.builder.*;
import org.scribe.builder.api.*;
import org.scribe.model.*;
import org.scribe.oauth.*;

import data.Tweet;

public class ScribeTest {

	private static final String URL = "https://stream.twitter.com/1/statuses/filter.json";

	private static final String apiKey = "cCXiLMMDa2gamP8YaUQcA";
	private static final String apiSecret = "B83alGdX1HbEDKVIhLebXW02TXB3H87HGAEb4dZNZc";

	private static final String token = "621835421-8KrKWswTS8RPYoAF2YhYagdLPqursnKadohkUinE";
	private static final String tokenSecret = "r1rLrgRfJ0taKJGh8vTrK2FXNDNMRb19jNr8DKtMSZ0";

	public static void main(String[] args) throws Exception {

		OAuthService service = new ServiceBuilder().provider(TwitterApi.class)
				.apiKey(apiKey).apiSecret(apiSecret).build();

		OAuthRequest request = new OAuthRequest(Verb.POST, URL);

		request.addBodyParameter("track", "poker");

		Token t = new Token(token, tokenSecret);

		service.signRequest(t, request);

		Response response = request.send();

		// BufferedReader input = new BufferedReader(new InputStreamReader(
		// response.getStream()));
		//
		// String inputLine = null;
		//
		// while ((inputLine = input.readLine()) != null)
		// System.out.println(inputLine);
		//
		// input.close();

		JSONTokener jsonTokener = new JSONTokener(new InputStreamReader(
				response.getStream(), "UTF-8"));

		while (true) {

			try {
				
				JSONObject jsonObject = new JSONObject(jsonTokener);
				
				System.out.println(new Tweet(jsonObject));
			}

			catch (JSONException ex) {
				throw new IOException("Got JSONException: " + ex.getMessage());
			}
		}

	}
}
