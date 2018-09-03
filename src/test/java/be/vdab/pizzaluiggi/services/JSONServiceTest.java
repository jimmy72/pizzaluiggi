package be.vdab.pizzaluiggi.services;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import be.vdab.pizzaluiggi.restclients.JSONClient;

@RunWith(MockitoJUnitRunner.class)
public class JSONServiceTest {
	private DefaultJSONService jsonService;
	@Mock
	private JSONClient dummyJSONClient;
	
	@Before
	public void before() throws JSONException {
		when(dummyJSONClient.getAJSONObject("test")).thenReturn(new JSONObject("{name:\"jimmy\"}"));
		jsonService = new DefaultJSONService(dummyJSONClient);
	}
	
	@Test
	public void getJSONObject() throws JSONException {
		assertTrue(jsonService.getAJSONObject("test").getString("name").equals("jimmy"));
		verify(dummyJSONClient).getAJSONObject("test");
	}
}
