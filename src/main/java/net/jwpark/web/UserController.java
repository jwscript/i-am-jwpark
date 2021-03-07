package net.jwpark.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.jwpark.domain.User;
import net.jwpark.domain.UserDao;

// 이 클래스가 컨트롤러라는 걸 명시하는 어노테이션 Controller
// 이 클래스에 있는 메서드의 URL의 앞부분이 '/users'로 매핑됨을 선언하는 어노테이션 RequestMapping
@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserDao userDao;

	@GetMapping("/loginForm")
	public String loginForm() {
		return "/user/login";
	}

	@PostMapping("/login")
	public String login(String userId, String password, HttpSession session) {
		User user = userDao.findByUserId(userId);
		if (user == null) {
			System.out.println("UserId Fail");
			return "redirect:/users/loginForm";
		}

		// userId가 존재하면 password 비교.
		if (!user.matchPassword(password)) {
			System.out.println("Password Fail");
			return "redirect:/users/loginForm";
		}

		System.out.println("Login Success");
		session.setAttribute(HttpSessionUtils.USER_SESSION_KEY, user); // userId, password 비교 완료시 세션에 데이터 저장.

		return "redirect:/";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute(HttpSessionUtils.USER_SESSION_KEY);

		return "redirect:/";
	}

	@GetMapping("/form")
	public String form() {
		return "/user/form";
	}

	@GetMapping("/{seq}/form")
	public String updateForm(@PathVariable Long seq, Model model, HttpSession session) {
		// URL에서 파라미터 값을 얻어오는 어노테이션 PathVariable
		if (!HttpSessionUtils.isLoginUser(session)) {
			return "redirect:/users/loginForm";
		}

		User sessionedUser = HttpSessionUtils.getUserFromSession(session);
		if (!sessionedUser.matchSeq(seq)) {
			throw new IllegalStateException("You can't update other user");
		}

		User user = userDao.findById(seq).get();
		model.addAttribute("user", user);

		return "/user/updateForm";
	}

	@PutMapping("/{seq}")
	public String update(@PathVariable Long seq, User updateUser, HttpSession session) {
		// URL에서 파라미터 값을 얻어오는 어노테이션 PathVariable
		if (!HttpSessionUtils.isLoginUser(session)) {
			return "redirect:/users/loginForm";
		}

		User sessionedUser = HttpSessionUtils.getUserFromSession(session);
		if (!sessionedUser.matchSeq(seq)) {
			throw new IllegalStateException("You can't update other user");
		}

		User user = userDao.findById(seq).get();
		user.update(updateUser);
		userDao.save(user);
		return "redirect:/users";
	}

	@PostMapping("")
	public String createUser(User user) {
		System.out.println("user : " + user);
		// users.add(user);
		userDao.save(user);
		return "redirect:/users";
	}

	@GetMapping("")
	public String getUserList(Model model) {
		model.addAttribute("users", userDao.findAll());
		return "/user/list";
	}
}

/*
 * 리턴값으로 경로를 주지 않으면 static 밑에서 일치하는 파일명을 찾아서 씀. 리턴값으로 경로를 주면 templates에서 해당 경로에
 * 일치하는 파일을 찾아서 씀.
 *
 */