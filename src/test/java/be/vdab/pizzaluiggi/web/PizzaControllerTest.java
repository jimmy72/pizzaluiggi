package be.vdab.pizzaluiggi.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.pizzaluiggi.services.EuroService;

@RunWith(MockitoJUnitRunner.class)
public class PizzaControllerTest {
	private PizzaController controller;
	@Mock
	private EuroService dummyEuroService;
		
	@Before
	public void before() {
		controller = new PizzaController(dummyEuroService);
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
	}
	@Test
	public void pizzaGeeftPizzaDoor() {
		ModelAndView modelAndView = controller.pizza(1);
		assertTrue(modelAndView.getModel().containsKey("pizza"));
	}
	@Test
	public void onbestaandePizza() {
		ModelAndView modelAndView = controller.pizza(-1);
		assertFalse(modelAndView.getModel().containsKey("pizza"));
	}

}
