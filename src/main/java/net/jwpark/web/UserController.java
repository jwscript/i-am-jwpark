package net.jwpark.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
	private List<User> users = new ArrayList<User>();

	@PostMapping("/create")
	public String createUser(User user) {
		System.out.println("user : " + user);
		users.add(user);
		return "redirect:/list";
	}

	@GetMapping("/list")
	public String getUserList(Model model) {
		model.addAttribute("users", users);
		return "list";
	}
}
