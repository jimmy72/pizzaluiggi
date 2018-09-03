package be.vdab.pizzaluiggi.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
import org.springframework.web.servlet.ModelAndView;

import be.vdab.pizzaluiggi.services.EuroService;
import be.vdab.pizzaluiggi.services.JSONService;

@RunWith(MockitoJUnitRunner.class)
public class PizzaControllerTest {
	
	private PizzaController controller;
	@Mock
	private JSONService dummyJSONService;
	@Mock
	private EuroService dummyEuroService;
		
	@Before
	public void before() throws JSONException {
		when(dummyJSONService.getAJSONObject("http://data.fixer.io/api/latest?access_key=3d0a4fecd7d5a53aa66487323c7dc519&symbols=USD")).thenReturn(new JSONObject("{name:\"Jimmy\"}"));
		controller = new PizzaController(dummyEuroService, dummyJSONService);
	}
	
	@Test
	public void pizzasWerktSamenMetPizzasDotJsp() {
		ModelAndView modelAndView = controller.pizzas();
		// werkt de method pizzas samen met de JSP (view) pizzas :
		assertEquals("pizzas", modelAndView.getViewName());
	}
	@Test
	public void pizzasGeeftPizzasDoor() {
		ModelAndView modelAndView = controller.pizzas();
		// geeft de method pizzas data door onder de naam pizzas aan de JSP:
		// het "model" onderdeel van ModelAndView gedraagt zich als een Map.
		// de key van een entry is de NAAM waaronder je data doorgeeft aan de JSP.
		// de value van een entry is de DATA ZELF die je doorgeeft aan de JSP.
		assertTrue(modelAndView.getModel().containsKey("pizzas"));
		
	}
	@Test
	public void pizzaWerktSamenMetPizzaDotJsp() {
		ModelAndView modelAndView = controller.pizza(1);
		assertEquals("pizza", modelAndView.getViewName());
		verify(dummyJSONService).getAJSONObject("http://data.fixer.io/api/latest?access_key=3d0a4fecd7d5a53aa66487323c7dc519&symbols=USD");
	}
	@Test
	public void pizzaGeeftPizzaDoor() {
		ModelAndView modelAndView = controller.pizza(1);
		assertTrue(modelAndView.getModel().containsKey("pizza"));
		verify(dummyJSONService).getAJSONObject("http://data.fixer.io/api/latest?access_key=3d0a4fecd7d5a53aa66487323c7dc519&symbols=USD");
	}
	@Test
	public void onbestaandePizza() {
		ModelAndView modelAndView = controller.pizza(-1);
		assertFalse(modelAndView.getModel().containsKey("pizza"));
	}

}
