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
import net.jwpark.domain.Result;
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
		Question question = questionDao.findById(seq).get();
		Result result = valid(session, question);
		if (!result.isValid()) {
			model.addAttribute("errorMessage", result.getErrorMessage());
			return "/user/login";
		}

		model.addAttribute("question", question);
		return "/qna/updateForm";
	}

	@PutMapping("/{seq}")
	public String update(@PathVariable Long seq, String title, String contents, Model model, HttpSession session) {
		Question question = questionDao.findById(seq).get();
		Result result = valid(session, question);
		if (!result.isValid()) {
			model.addAttribute("errorMessage", result.getErrorMessage());
			return "/user/login";
		}

		question.update(title, contents);
		questionDao.save(question);
		return String.format("redirect:/questions/%d", seq);
	}

	@DeleteMapping("/{seq}")
	public String delete(@PathVariable Long seq, Model model, HttpSession session) {
		Question question = questionDao.findById(seq).get();
		Result result = valid(session, question);
		if (!result.isValid()) {
			model.addAttribute("errorMessage", result.getErrorMessage());
			return "/user/login";
		}

		questionDao.deleteById(seq);
		return "redirect:/";
	}

	private Result valid(HttpSession session, Question question) {
		if (!HttpSessionUtils.isLoginUser(session)) {
			return Result.fail("You need to login.");
		}

		User loginUser = HttpSessionUtils.getUserFromSession(session);
		if (!question.isSameWriter(loginUser)) {
			return Result.fail("You need to access auth");
		}

		return Result.ok();
	}
}
