package be.vdab.pizzaluiggi.web;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.pizzaluiggi.entities.Pizza;

@Controller
@RequestMapping("pizzas")
class PizzaController {
	private static final String PIZZAS_VIEW = "pizzas";
	private final Map<Long, Pizza> pizzas = new LinkedHashMap<>();
	
	@GetMapping
	ModelAndView pizzas() {
		pizzas.put(1L, new Pizza(1, "Prosciutto", BigDecimal.valueOf(4), true));
		pizzas.put(2L, new Pizza(2, "Margherita", BigDecimal.valueOf(5), false));
		pizzas.put(3L, new Pizza(3, "Calzone", BigDecimal.valueOf(4), false));
		pizzas.put(4L, new Pizza(4, "Fungi & Olive", BigDecimal.valueOf(5), false));
		return new ModelAndView(PIZZAS_VIEW, "pizzas", pizzas);
	}
}
