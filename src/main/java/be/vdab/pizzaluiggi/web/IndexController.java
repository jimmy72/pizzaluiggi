package be.vdab.pizzaluiggi.web;

import java.time.LocalTime;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//hier komen enkele imports. Eclipse voegt ontbrekende imports toe
//aan je source met de sneltoetsen shift+ctrl+o.
@Controller
@RequestMapping("/")
class IndexController {
	@GetMapping 
	String index() { 
		return "/WEB-INF/JSP/index.jsp";
	}
}
