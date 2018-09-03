package be.vdab.pizzaluiggi.services;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import be.vdab.pizzaluiggi.exceptions.JSONClientException;
import be.vdab.pizzaluiggi.restclients.JSONClient;

@Service
public class DefaultJSONService implements JSONService {

	private final JSONClient jsonClient;
	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultJSONService.class);
	
	public DefaultJSONService(JSONClient jsonClient) {
		this.jsonClient = jsonClient;
	}
	
	@Override
	public JSONObject getAJSONObject(String url) {
		
		try {
			return jsonClient.getAJSONObject(url);
		} catch (JSONClientException ex) {
		  LOGGER.error("kan JSONObject niet lezen", ex);
		  return null;
		}
		
	}
}
