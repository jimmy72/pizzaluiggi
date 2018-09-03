package be.vdab.pizzaluiggi.restclients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;

import be.vdab.pizzaluiggi.exceptions.JSONClientException;


@Component
public class DefaultJSONClient implements JSONClient {

	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultJSONClient.class);
	
	
	public DefaultJSONClient() {
		
	}
	
	@Override
	public JSONObject getAJSONObject(String strUrl) {
		URL url;
		try {
			url = new URL(strUrl);
		} catch (MalformedURLException e) {
			String fout = "URL is verkeerd.";
			LOGGER.error(fout, e);
			throw new JSONClientException(fout);
		}
		
		try ( InputStream is = url.openStream();
	    	  InputStreamReader isr = new InputStreamReader(is, Charset.forName("UTF-8"));
	    	  BufferedReader bufferedReader = new BufferedReader(isr)){
	      
	    	String jsonText = readAll(bufferedReader);
			JSONObject json = new JSONObject(jsonText);
			LOGGER.info("JSONObject opgehaald via DefaultJSONClient");
			return json;
			
	    } catch (IOException ex) {
	    	String fout = "Kan niet lezen (I/O exception";
			LOGGER.error(fout, ex); 
			throw new JSONClientException(fout);
		}  
	}
	
	
	private String readAll(Reader rd) throws IOException {
	    StringBuilder sb = new StringBuilder();
	    int cp;
	    while ((cp = rd.read()) != -1) {
	      sb.append((char) cp);
	    }
	    return sb.toString();
	  }

}
