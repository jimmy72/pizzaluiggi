package be.vdab.pizzaluiggi.web;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.pizzaluiggi.entities.Pizza;
import be.vdab.pizzaluiggi.services.EuroService;
import be.vdab.pizzaluiggi.services.JSONService;
import be.vdab.pizzaluiggi.services.PizzaService;

@Controller
@RequestMapping("pizzas")
class PizzaController {
	private static final String PIZZAS_VIEW = "pizzas";
	private static final String PIZZA_VIEW = "pizza";
	private static final String PRIJZEN_VIEW = "prijzen";
	private static final String VAN_TOT_PRIJS_VIEW = "vantotprijs";
	private final EuroService euroService;
	private final PizzaService pizzaService;
	//private final JSONService jsonService;
	
		
//	PizzaController(EuroService euroService, JSONService  jsonService){
//		this.euroService = euroService;
//		this.jsonService = jsonService;
//	}
	
	PizzaController(EuroService euroService, PizzaService  pizzaService){
		this.euroService = euroService;
		this.pizzaService = pizzaService;
	}
	
	
	@GetMapping
	ModelAndView pizzas() {
		return new ModelAndView(PIZZAS_VIEW, "pizzas", pizzaService.findAll());
	}
	
	@GetMapping("{id}") 
	ModelAndView pizza(@PathVariable long id) { 
		ModelAndView modelAndView = new ModelAndView(PIZZA_VIEW);
		pizzaService.read(id).ifPresent(pizza -> {
			modelAndView.addObject("pizza", pizza);
			modelAndView.addObject("inDollar", euroService.naarDollar(pizza.getPrijs()));
		});
		return modelAndView;
//			modelAndView.addObject("jsonattribuut", jsonService.getAJSONObject(
//					"http://data.fixer.io/api/latest?access_key=3d0a4fecd7d5a53aa66487323c7dc519&symbols=USD"));
		
	}
	
	@GetMapping(path = "/prijzen") 
		ModelAndView prijzen() {
		return new ModelAndView(PRIJZEN_VIEW, "prijzen", pizzaService.findUniekePrijzen());
	}
	
	@GetMapping(params = "prijs") 
	ModelAndView pizzasVanPrijs(BigDecimal prijs) {
	return new ModelAndView(PRIJZEN_VIEW, "pizzas", pizzaService.findByPrijs(prijs)) 
		.addObject("prijs", prijs)
		.addObject("prijzen", pizzaService.findUniekePrijzen());	
	}
	
	@GetMapping(path = "/vantotprijs") 
	ModelAndView findVanTotPrijs() {
		VanTotPrijsForm form = new VanTotPrijsForm();
		//form.setVan(BigDecimal.ZERO);
		//form.setTot(BigDecimal.ZERO);
		return new ModelAndView(VAN_TOT_PRIJS_VIEW).addObject(form);
	}
	
	@GetMapping(path = "/vantotprijs", params = {"van", "tot"}) 
	ModelAndView findVanTotPrijs(VanTotPrijsForm form, BindingResult bindingResult) { 
		ModelAndView modelAndView = new ModelAndView(VAN_TOT_PRIJS_VIEW);
		if(bindingResult.hasErrors()) {
			return modelAndView; 
		}
		List<Pizza> pizzas = pizzaService.findByPrijsBetween(form.getVan(), form.getTot());
		if(pizzas.isEmpty()) {
			bindingResult.reject("geenPizzas");
		}else {
			modelAndView.addObject("pizzas", pizzas);
		}
		return modelAndView;
	}
	
	
}
