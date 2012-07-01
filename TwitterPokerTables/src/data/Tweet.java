package data;

import org.json.JSONException;
import org.json.JSONObject;

public class Tweet {

	protected Entities mEntities;

	public Tweet(JSONObject pJsonObject) {

		try {
			mEntities = new Entities(pJsonObject.getJSONObject("entities"));
		}

		catch (JSONException e) {
		}
	}
}
