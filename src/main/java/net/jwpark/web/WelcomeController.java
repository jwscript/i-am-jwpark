package net.jwpark.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {

	@GetMapping("/helloworld")
	public String callWelcome(String name, int age, Model model) {
		/*
		 * 1. 파라미터로 전달받는 변수명이 URL QueryString으로 넘어올 떄의 키와 같아야 함.
		 * ㄴ 단, Model 참조변수는 쿼리스트링으로 전달받는 데이터가 아님. 반드시 필요하지는 않다는 것.
		 */
		System.out.println("name: " + name + ", age: " + age);
		model.addAttribute("name", name);
		model.addAttribute("age", age);
		return "welcome"; // welcome.html 호출. 파일명만 잘 반환하면 됨.
	}
}

/*
 * Controller 어노테이션은 다른 클래스에서 써도 가능?
 * 
 * Mapping이 겹치면 어떻게 되는지?
 */