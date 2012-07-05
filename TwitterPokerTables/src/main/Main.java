package main;

import java.io.IOException;
import java.io.InputStreamReader;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.TwitterApi;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

import utils.HTMLCreator;

import data.Tweet;

public class Main {

	private static final String URL = "https://stream.twitter.com/1/statuses/filter.json";

	private static final String apiKey = "cCXiLMMDa2gamP8YaUQcA";
	private static final String apiSecret = "B83alGdX1HbEDKVIhLebXW02TXB3H87HGAEb4dZNZc";

	private static final String token = "621835421-8KrKWswTS8RPYoAF2YhYagdLPqursnKadohkUinE";
	private static final String tokenSecret = "r1rLrgRfJ0taKJGh8vTrK2FXNDNMRb19jNr8DKtMSZ0";

	public static void main(String[] args) throws Exception {

		OAuthService service = new ServiceBuilder().provider(TwitterApi.class)
				.apiKey(apiKey).apiSecret(apiSecret).build();

		OAuthRequest request = new OAuthRequest(Verb.POST, URL);

		request.addBodyParameter("track", "wsop");

		Token t = new Token(token, tokenSecret);

		service.signRequest(t, request);

		Response response = request.send();

		JSONTokener jsonTokener = new JSONTokener(new InputStreamReader(
				response.getStream(), "UTF-8"));

		int i = 0;
		
		StringBuilder sb = new StringBuilder();
		
		while (i++ < 5) {

			try {
				
				JSONObject jsonObject = new JSONObject(jsonTokener);
				
				sb.append(new Tweet(jsonObject).getHTML() + "\n");
			}

			catch (JSONException ex) {
				throw new IOException("Got JSONException: " + ex.getMessage());
			}
		}
		
		System.out.println(HTMLCreator.createHTML(sb));
	}
}
