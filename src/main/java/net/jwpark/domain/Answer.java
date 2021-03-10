package net.jwpark.domain;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Answer extends AbstractEntity{
	@ManyToOne // Answer many가 하나의 User에 매핑가능.
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_writer"))
	@JsonProperty
	private User writer;

	@ManyToOne // Answer many가 하나의 Question에 매핑 가능.
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_answer_to_question"))
	@JsonProperty
	private Question question;
	
	@Lob
	@JsonProperty
	private String contents;
	
	public Answer() {
	}

	public Answer(User writer, Question question, String contents) {
		super();
		this.writer = writer;
		this.question = question;
		this.contents = contents;
	}
	
	@Override
	public String toString() {
		return "Answer [" + super.toString() + ", writer=" + writer + ", contents=" + contents + "]";
	}

	public boolean isSameWriter(User loginUser) {
		return this.writer.equals(loginUser);
	}

}
