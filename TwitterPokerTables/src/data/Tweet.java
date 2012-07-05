package data;

import org.json.JSONException;
import org.json.JSONObject;

import utils.TweetsEmbedder;

public class Tweet {

	protected String mId;
	protected String mText;
	protected User mUser;
	protected Entities mEntities;
	protected Place mPlace;
	protected String mEmbed;
	
	public Tweet(JSONObject pJsonObject) {

		try {
			mId = pJsonObject.getString("id_str");
		}catch (JSONException e) {}
		
		try {
			mText = pJsonObject.getString("text");
		}catch (JSONException e) {}
		
		try {
			mUser = new User(pJsonObject.getJSONObject("user"));
		}catch (JSONException e) {}
		
		try {
			mEntities = new Entities(pJsonObject.getJSONObject("entities"));
		}catch (JSONException e) {}
		
		try {
			mPlace = new Place(pJsonObject.getJSONObject("place"));
		}catch (JSONException e) {}
		
		try {
			mEmbed = TweetsEmbedder.getEmbedCode(mId);
		}catch (Exception e) {}
	}
	
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("Tweet #" + mId + ": " + mText + "\n");
		sb.append("User: " + mUser + "\n");
		sb.append("Entities:" + mEntities + "\n");
		sb.append("Place:" + mPlace + "\n");
		
		sb.append("\n");
		
		return sb.toString();
	}
	
	public String getHTML() {		
		return mEmbed;
	}
}
