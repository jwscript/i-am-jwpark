package net.jwpark.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import net.jwpark.domain.User;
import net.jwpark.domain.UserDao;

@Controller
public class UserController {

	@Autowired
	private UserDao userDao;

	@PostMapping("/create")
	public String createUser(User user) {
		System.out.println("user : " + user);
		// users.add(user);
		userDao.save(user);
		return "redirect:/list";
	}

	@GetMapping("/list")
	public String getUserList(Model model) {
		model.addAttribute("users", userDao.findAll());
		return "list";
	}
}
