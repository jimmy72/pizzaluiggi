package be.vdab.pizzaluiggi.web;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
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
	private final AtomicInteger numberOfViews = new AtomicInteger(0);
	@GetMapping 
	ModelAndView index(@CookieValue(name = "laatstBezocht" , required = false) String laatstBezocht, HttpServletResponse response) { 
		String message;
		int hour = LocalTime.now().getHour();
		message = (hour >= 6 && hour < 12) ? "Goede morgen" :
			(hour >= 12 && hour < 18) ? "Goede middag" :
			(hour >= 0 && hour < 6) ? "Goede nacht" : "Goede avond";
		Cookie cookie = new Cookie("laatstBezocht", LocalDateTime.now().toString());
		cookie.setMaxAge(31_536_000);
		response.addCookie(cookie);
		ModelAndView mv = new ModelAndView(JSP_FILE, "message", message)
			.addObject("zaakvoerder", new Persoon("Luiggi", "Peperone", 7, true,
				new Adres("Genkersteenweg", "285", 3600, "Genk")));
		if(laatstBezocht != null) {
			mv.addObject("laatstBezocht", laatstBezocht);
		}
		mv.addObject("numberOfViews", numberOfViews.incrementAndGet());
		return mv;
	}
}
