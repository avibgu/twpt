package main;

import com.twitterapime.rest.Credential;
import com.twitterapime.rest.UserAccountManager;
import com.twitterapime.xauth.Token;
import com.twitterapime.xauth.ui.OAuthDialogListener;
import com.twitterapime.xauth.ui.OAuthDialogWrapper;

public class APIMETest {

	public static void main(String[] args) {

//		MIDlet midlet = null;

		OAuthDialogWrapper page = null;

		page.setConsumerKey("cCXiLMMDa2gamP8YaUQcA");

		page.setConsumerSecret("B83alGdX1HbEDKVIhLebXW02TXB3H87HGAEb4dZNZc");

		page.setOAuthListener(new OAuthDialogListener() {

			/** Callback when user authorizes the app to access the account. */

			public void onAuthorize(Token token) {

				Credential c = new Credential("cCXiLMMDa2gamP8YaUQcA",
						"B83alGdX1HbEDKVIhLebXW02TXB3H87HGAEb4dZNZc", token);

				UserAccountManager m = UserAccountManager.getInstance(c);

//				if (m.verifyCredential()) {
//
//					// user authorized!
//
//				}
			}

			/** Callback when user denies the app to access the account. */

			public void onAccessDenied(String message) {
			}

			/** Callback when any error happens during authentication. */

			public void onFail(String message, String description) {
			}

		});

		page.login(); // runs asynchronously.
	}
}
