package be.vdab.pizzaluiggi.restclients;

import org.json.JSONObject;

public interface JSONClient {
	JSONObject getAJSONObject(String url);
}
