package net.jwpark.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.jwpark.domain.Question;
import net.jwpark.domain.QuestionDao;
import net.jwpark.domain.User;

@Controller
@RequestMapping("/questions")
public class QuestionController {

	@Autowired
	private QuestionDao questionDao;

	@GetMapping("/form")
	public String form(HttpSession session) {
		if (!HttpSessionUtils.isLoginUser(session)) {
			return "/users/loginForm";
		}

		return "/qna/form";
	}

	@PostMapping("")
	public String create(String title, String contents, HttpSession session) {
		if (!HttpSessionUtils.isLoginUser(session)) {
			return "redirect:/users/loginForm";
		}

		User sessionedUser = HttpSessionUtils.getUserFromSession(session);
		Question newQuestion = new Question(sessionedUser, title, contents);
		questionDao.save(newQuestion);

		return "redirect:/";
	}

	@GetMapping("/{seq}")
	public String show(@PathVariable Long seq, Model model) {
		model.addAttribute("question", questionDao.findById(seq).get());

		return "/qna/show";
	}

	@GetMapping("/{seq}/form")
	public String updateForm(@PathVariable Long seq, Model model, HttpSession session) {
		try {
			Question question = questionDao.findById(seq).get();
			hasPermission(session, question);
			model.addAttribute("question", question);
			return "/qna/updateForm";
		} catch (IllegalStateException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "/user/login";
		}
	}

	@PutMapping("/{seq}")
	public String update(@PathVariable Long seq, String title, String contents, Model model, HttpSession session) {
		try {
			Question question = questionDao.findById(seq).get();
			hasPermission(session, question);
			question.update(title, contents);
			questionDao.save(question);
			return String.format("redirect:/questions/%d", seq);
		} catch (IllegalStateException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "/user/login";
		}
	}

	@DeleteMapping("/{seq}")
	public String delete(@PathVariable Long seq, Model model, HttpSession session) {
		try {
			Question question = questionDao.findById(seq).get();
			hasPermission(session, question);
			questionDao.deleteById(seq);
			return "redirect:/";
		} catch (IllegalStateException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "/user/login";
		}
	}

	private boolean hasPermission(HttpSession session, Question question) {
		if (!HttpSessionUtils.isLoginUser(session)) {
			throw new IllegalStateException("You need to login.");
		}

		User loginUser = HttpSessionUtils.getUserFromSession(session);
		if (!question.isSameWriter(loginUser)) {
			throw new IllegalStateException("You need to access auth");
		}

		return true;
	}
}
