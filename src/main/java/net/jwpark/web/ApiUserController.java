package net.jwpark.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.jwpark.domain.User;
import net.jwpark.domain.UserDao;

@RestController
@RequestMapping("/api/users")
public class ApiUserController {

	@Autowired
	private UserDao userDao;

	@GetMapping("/{seq}")
	public User show(@PathVariable Long seq) {
		return userDao.findById(seq).get();
	}
}
