package utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class TweetsEmbedder {

	public static String SCRIPT = "<script src=\"//platform.twitter.com/widgets.js\" charset=\"utf-8\"></script>";

	public static String getEmbedCode(String pId) throws Exception {

		URL url = new URL(" https://api.twitter.com/1/statuses/oembed.json?id="
				+ pId + "omit_script=true");

		HttpURLConnection httpc = (HttpURLConnection) url.openConnection();

		httpc.setRequestMethod("GET");

		httpc.setDoOutput(true);
		httpc.setDoInput(true);

		httpc.connect();

		JSONTokener jsonTokener = new JSONTokener(new InputStreamReader(
				httpc.getInputStream(), "UTF-8"));

		String embeddedCode = "";

		try {

			JSONObject jsonObject = new JSONObject(jsonTokener);

			System.out.println(jsonObject);

			embeddedCode = jsonObject.getString("html");
		}

		catch (JSONException ex) {
			throw new IOException("Got JSONException: " + ex.getMessage());
		}

		return embeddedCode;
	}

}
