package net.jwpark.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Question extends AbstractEntity {
	@ManyToOne // Question n개가 User 1개에 매칭 될 수 있다는 것.
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_question_writer"))
	@JsonProperty
	private User writer;

	@JsonProperty
	private String title;

	@Lob // 255자 보다 긴 데이터를 넣을 수 있게 해줌.
	@JsonProperty
	private String contents;

	@JsonProperty
	private Integer countOfAnswer = 0;

	@OneToMany(mappedBy = "question") // Answer.class에서 매핑할 필드 이름을 써야함.
	@OrderBy("seq ASC")
	private List<Answer> answers;

	public Question() {
	}

	public Question(User writer, String title, String contents) {
		super();
		this.writer = writer;
		this.title = title;
		this.contents = contents;
	}

	public void update(String title, String contents) {
		this.title = title;
		this.contents = contents;
	}

	public boolean isSameWriter(User loginUser) {
		return this.writer.equals(loginUser);
	}

	public void addAnswer() {
		this.countOfAnswer += 1;
	}

	public void deleteAnswer() {
		this.countOfAnswer -= 1;
	}
}
