package net.jwpark.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.jwpark.domain.Answer;
import net.jwpark.domain.AnswerDao;
import net.jwpark.domain.Question;
import net.jwpark.domain.QuestionDao;
import net.jwpark.domain.User;

@Controller
@RequestMapping("/questions/{questionSeq}/answers") // Answer가 Question에 종속적이라서 Url을 이런 형태로 만드는 것이 좋다.
public class AnswerController {
	@Autowired
	private QuestionDao questionDao;

	@Autowired
	private AnswerDao answerDao;

	@PostMapping("")
	public String create(@PathVariable Long questionSeq, String contents, HttpSession session) {
		if (!HttpSessionUtils.isLoginUser(session)) {
			return "/users/loginForm";
		}

		User loginUser = HttpSessionUtils.getUserFromSession(session);
		Question question = questionDao.findById(questionSeq).get();
		Answer answer = new Answer(loginUser, question, contents);
		answerDao.save(answer);

		return String.format("redirect:/questions/%d", questionSeq);
	}

}
