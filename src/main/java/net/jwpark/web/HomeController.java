package net.jwpark.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import net.jwpark.domain.QuestionDao;

@Controller
public class HomeController {

	@Autowired
	private QuestionDao questionDao; // 질문에 목록에 조회하기 위해 db에서 질문 목록 받아올 객체.

	@GetMapping("")
	public String home(Model model) {
		model.addAttribute("questions", questionDao.findAll());

		return "index"; // 이렇게 반환하면 static/index.html 을 찾아감.
	}
}
