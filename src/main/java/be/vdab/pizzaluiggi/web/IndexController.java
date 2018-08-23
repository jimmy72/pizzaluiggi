package be.vdab.pizzaluiggi.web;

import java.time.LocalTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//hier komen enkele imports. Eclipse voegt ontbrekende imports toe
//aan je source met de sneltoetsen shift+ctrl+o.
@RestController
@RequestMapping("/")
class IndexController {
	@GetMapping 
	String index() { 
		int uur = LocalTime.now().getHour();
		return 	(uur >= 6 && uur < 12)  ? "Goede morgen!" :
				(uur >= 12 && uur < 18) ? "Goede middag!" : 
				(uur >= 0 && uur < 6)   ? "Goede nacht!"  : "Goede avond!";
	}
}
