package net.jwpark.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.jwpark.domain.Answer;
import net.jwpark.domain.AnswerDao;
import net.jwpark.domain.Question;
import net.jwpark.domain.QuestionDao;
import net.jwpark.domain.User;

// spring 컨트롤러 반환하는 데이터를 json으로 바꿔서 반환하려면 RestController를 써야 한다.
@RestController
@RequestMapping("/api/questions/{questionSeq}/answers")
public class ApiAnswerController {

	@Autowired
	private QuestionDao questionDao;

	@Autowired
	private AnswerDao answerDao;

	@PostMapping("")
	public Answer create(@PathVariable Long questionSeq, String contents, HttpSession session) {
		if (!HttpSessionUtils.isLoginUser(session)) {
			return null;
		}

		User loginUser = HttpSessionUtils.getUserFromSession(session);
		Question question = questionDao.findById(questionSeq).get();
		Answer answer = new Answer(loginUser, question, contents);
		return answerDao.save(answer);
	}

}
