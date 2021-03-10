package net.jwpark.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.jwpark.domain.Answer;
import net.jwpark.domain.AnswerDao;
import net.jwpark.domain.Question;
import net.jwpark.domain.QuestionDao;
import net.jwpark.domain.Result;
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
			return null; // TODO: 이렇게 작업한 경우, create actio에 대한 반환값이 일관성이 없음.
		}

		User loginUser = HttpSessionUtils.getUserFromSession(session);
		Question question = questionDao.findById(questionSeq).get();
		Answer answer = new Answer(loginUser, question, contents);
		question.addAnswer();
		return answerDao.save(answer);
		
		/**
		 *  question에 answer가 달렸으니 question 객체 내의 countOfAnswer는 +1이 되었다.
		 *  그런데 question을 update하는 로직은 동작하지 않은 상태이다.
		 *  이때. answer를 save하였는데, Question도 변경된 값이 save되었다 이유는 무엇일까?
		 *  => JPA에 대한 공부가 필요함.
		 */
	}

	@DeleteMapping("/{seq}")
	public Result delete(@PathVariable Long questionSeq, @PathVariable Long seq, HttpSession session) {
		System.out.println("step 1");
		if (!HttpSessionUtils.isLoginUser(session)) {
			System.out.println("step 2");
			return Result.fail("You need to login.");
		}

		System.out.println("step 3");
		User loginUser = HttpSessionUtils.getUserFromSession(session);
		Answer answer = answerDao.findById(seq).get();
		if (!answer.isSameWriter(loginUser)) {
			System.out.println("step 4");
			return Result.fail("You need to access auth.");
		}

		System.out.println("step 5");
		answerDao.deleteById(seq);
		
		Question question = questionDao.findById(questionSeq).get();
		question.deleteAnswer();
		questionDao.save(question);
		
		return Result.ok();
	}
}
