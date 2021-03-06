package net.jwpark.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("")
	public String home() {
		return "index"; // 이렇게 반환하면 static/index.html 을 찾아감. 
	}
}
