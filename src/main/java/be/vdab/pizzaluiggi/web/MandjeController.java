package be.vdab.pizzaluiggi.web;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.pizzaluiggi.entities.Pizza;
import be.vdab.pizzaluiggi.services.PizzaService;

@Controller
@RequestMapping(value = "/mandje")
class MandjeController {
	private final static String VIEW = "mandje";
	private final static String REDIRECT_NA_TOEVOEGEN= "redirect:/mandje";
	private final Mandje mandje;
	private final PizzaService pizzaService;
	
	MandjeController(Mandje mandje, PizzaService pizzaService){
		this.mandje = mandje;
		this.pizzaService = pizzaService;
	}
	
	private List<Pizza> maakPizzasVanPizzaIds(List<Long> pizzaIds){
		List<Pizza> pizzas = new ArrayList<>(pizzaIds.size());
		pizzaIds.forEach(id -> pizzaService.read(id).ifPresent(pizza -> pizzas.add(pizza)));
		return pizzas;		
	}
	
	@GetMapping
	ModelAndView toonMandje() {
		return new ModelAndView(VIEW)
				.addObject(new MandjeForm())
				.addObject("allePizzas", pizzaService.findAll())
				.addObject("pizzasInMandje", maakPizzasVanPizzaIds(this.mandje.getPizzaIds()));
	}
	@PostMapping(params = "pizzaId")
	String voegPizzaToeAanMandje(@Valid @ModelAttribute("mandjeForm") MandjeForm form) {
		mandje.addPizzaId(form.getPizzaId());
		return REDIRECT_NA_TOEVOEGEN;
	}
	
	
}
