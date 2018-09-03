package be.vdab.pizzaluiggi.web;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.pizzaluiggi.entities.Pizza;
import be.vdab.pizzaluiggi.services.EuroService;
import be.vdab.pizzaluiggi.services.JSONService;

@Controller
@RequestMapping("pizzas")
class PizzaController {
	private static final String PIZZAS_JSP = "pizzas";
	private static final String PIZZA_DETAIL_JSP = "pizza";
	private static final String PRIJZEN_JSP = "prijzen";
	private final EuroService euroService;
	private final JSONService jsonService;
	
	private final Map<Long, Pizza> pizzas = new LinkedHashMap<>();
	
	PizzaController(EuroService euroService, JSONService jsonService){
		pizzas.put(1L, new Pizza(1, "Prosciutto", BigDecimal.valueOf(4), true));
		pizzas.put(2L, new Pizza(2, "Margherita", BigDecimal.valueOf(5), false));
		pizzas.put(3L, new Pizza(3, "Calzone", BigDecimal.valueOf(4), false));
		pizzas.put(4L, new Pizza(4, "Quattro Formagi", BigDecimal.valueOf(5), false));
		pizzas.put(23L, new Pizza(23, "Fungi & Olive", BigDecimal.valueOf(5), false));
		this.euroService = euroService;
		this.jsonService = jsonService;
	}
	
	
	@GetMapping
	ModelAndView pizzas() {
		return new ModelAndView(PIZZAS_JSP, "pizzas", pizzas);
	}
	
	@GetMapping("{id}") 
	ModelAndView pizza(@PathVariable long id) { 
		ModelAndView modelAndView = new ModelAndView(PIZZA_DETAIL_JSP);
		if (pizzas.containsKey(id)) {
			Pizza pizza = pizzas.get(id);
			modelAndView.addObject("pizza", pizza); //value van Map is Pizza object
			//indien er geen naam voor object wordt meegegeven dan wordt aan jsp de naam van het object
			//meegegeven maar dan in kleine letters
			modelAndView.addObject("inDollar", euroService.naarDollar(pizza.getPrijs()));
			modelAndView.addObject("jsonattribuut", jsonService.getAJSONObject(
					"http://data.fixer.io/api/latest?access_key=3d0a4fecd7d5a53aa66487323c7dc519&symbols=USD"));
		}
		return modelAndView;
	}
	
	@GetMapping("prijzen") 
		ModelAndView prijzen() {
		return new ModelAndView(PRIJZEN_JSP, "prijzen", 
			pizzas.values()
				.stream()
				.map(pizza -> pizza.getPrijs())
				.distinct()
				.collect(Collectors.toSet()));
	}
	
	@GetMapping(params = "prijs") 
	ModelAndView pizzasVanPrijs(BigDecimal prijs) {
	return new ModelAndView(PRIJZEN_JSP, "pizzas",
		pizzas.values()
			.stream()
			.filter(p -> p.getPrijs().equals(prijs))
			.collect(Collectors.toList())) 
		.addObject("prijs", prijs)
		.addObject("prijzen", 
			pizzas.values()
				.stream()
				.map(pizza -> pizza.getPrijs())
				.distinct()
				.collect(Collectors.toSet()));	
	}
}
