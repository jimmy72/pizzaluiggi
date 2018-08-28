package be.vdab.pizzaluiggi.web;

import java.time.LocalTime;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.pizzaluiggi.valueobjects.Adres;
import be.vdab.pizzaluiggi.valueobjects.Persoon;

//Eclipse voegt ontbrekende imports toe aan je source met de sneltoetsen shift+ctrl+o.

@Controller
@RequestMapping("/")
class IndexController { 
	
	private static final String JSP_FILE = "index";
	private static final String MESSAGE_NAME = "message";
	
	@GetMapping 
	ModelAndView index() { 
		String message;
		int hour = LocalTime.now().getHour();
		message = (hour >= 6 && hour < 12) ? "Goede morgen" :
			(hour >= 12 && hour < 18) ? "Goede middag" :
			(hour >= 0 && hour < 6) ? "Goede nacht" : "Goede avond";
		return new ModelAndView(JSP_FILE, MESSAGE_NAME, message)
			.addObject("zaakvoerder", new Persoon("Luiggi", "Peperone", 7, true,
				new Adres("Genkersteenweg", "285", 3600, "Genk")));
	}
}
