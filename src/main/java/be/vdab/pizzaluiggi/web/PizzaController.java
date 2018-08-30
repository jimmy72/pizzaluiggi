package be.vdab.pizzaluiggi.web;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.pizzaluiggi.entities.Pizza;

@Controller
@RequestMapping("pizzas")
class PizzaController {
	private static final String PIZZAS_VIEW = "pizzas";
	private static final String PIZZA_VIEW = "pizza";
	
	private final Map<Long, Pizza> pizzas = new LinkedHashMap<>();
	
	@GetMapping
	ModelAndView pizzas() {
		pizzas.put(1L, new Pizza(1, "Prosciutto", BigDecimal.valueOf(4), true));
		pizzas.put(2L, new Pizza(2, "Margherita", BigDecimal.valueOf(5), false));
		pizzas.put(3L, new Pizza(3, "Calzone", BigDecimal.valueOf(4), false));
		pizzas.put(4L, new Pizza(4, "Quattro Formagi", BigDecimal.valueOf(5), false));
		pizzas.put(23L, new Pizza(23, "Fungi & Olive", BigDecimal.valueOf(5), false));
		return new ModelAndView(PIZZAS_VIEW, "pizzas", pizzas);
	}
	
	@GetMapping("{id}") 
	ModelAndView pizza(@PathVariable long id) { 
		ModelAndView modelAndView = new ModelAndView(PIZZA_VIEW);
		if (pizzas.containsKey(id)) {
			modelAndView.addObject(pizzas.get(id)); //value van Map is Pizza object
			//indien er geen naam voor object wordt meegegeven dan wordt aan jsp de naam van het object
			//meegegeven maar dan in kleine letters
			//modelAndView.addObject("pizza", pizzas.get(id));
		}
		return modelAndView;
	}
}
