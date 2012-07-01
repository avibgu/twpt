package data;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Entities {

	protected List<String> mHashtags;
	protected List<String> mUserMentions;

	public Entities(JSONObject pJsonObject) {

		mHashtags = new ArrayList<String>();

		try {
			retrieveHashTags(pJsonObject.getJSONArray("hashtags"));
		}

		catch (JSONException e) {
		}

		mUserMentions = new ArrayList<String>();

		try {
			retrieveUserMentions(pJsonObject.getJSONArray("user_mentions"));
		}

		catch (JSONException e) {
		}
	}

	protected void retrieveHashTags(JSONArray pJsonArray) throws JSONException {
		
		for (int i = 0; i < pJsonArray.length(); i++)
			mHashtags.add(pJsonArray.getJSONObject(i).getString("text"));
	}

	protected void retrieveUserMentions(JSONArray pJsonArray)
			throws JSONException {
		
		for (int i = 0; i < pJsonArray.length(); i++)
			mHashtags.add(pJsonArray.getJSONObject(i).getString("screen_name"));
		
		// TODO: should change screen_name to name?..
	}
}
