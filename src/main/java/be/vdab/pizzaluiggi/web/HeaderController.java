package be.vdab.pizzaluiggi.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("headers")
class HeaderController {

	private static final String JSP_FILE = "headers";
	
	@GetMapping
	ModelAndView opWindows(@RequestHeader("user-agent") String userAgent) {
		return new ModelAndView(JSP_FILE,"opWindows",userAgent.toLowerCase().contains("windows"));
	}
}
