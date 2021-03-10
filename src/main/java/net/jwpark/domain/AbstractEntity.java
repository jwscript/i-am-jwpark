package net.jwpark.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonProperty;

@MappedSuperclass // jpa 에서 부모 클래스에 해당하는 클래스로 사용할 때, 사용할 수 있는 어노테이션
@EntityListeners(AuditingEntityListener.class) // save 시, 데이터의 변경이 발생헤야 하는 경우 어노테이션에따라 처리해줌.
public class AbstractEntity {
	@Id // PK 설정용 어노테이
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 가장 최근에 추가된 값에서 +1 처리하는 전략 세팅.
	@JsonProperty
	private Long seq;

	// 생성된 시간을 입력.
	@CreatedDate
	private LocalDateTime createdDate;

	// 마지막으로 수정된 시간을 입력.
	@LastModifiedDate
	private LocalDateTime modifiedDate;

	public Long getSeq() {
		return this.seq;
	}

	public LocalDateTime getCreateDate() {
		return this.createdDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((seq == null) ? 0 : seq.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractEntity other = (AbstractEntity) obj;
		if (seq == null) {
			if (other.seq != null)
				return false;
		} else if (!seq.equals(other.seq))
			return false;
		return true;
	}

	// formattedCreatedDate 라는 변수의 get이라고 생각하면 템플릿에서 어떻게 써야할지 이해할 수 있음.
	public String getFormattedCreatedDate() {
		return this.getFormattedDateTime(this.createdDate, "yyyy.MM.dd HH:mm:ss");
	}

	public String getFormattedModifiedDate() {
		return this.getFormattedDateTime(this.modifiedDate, "yyyy.MM.dd HH:mm:ss");
	}

	private String getFormattedDateTime(LocalDateTime dateTime, String format) {
		if (dateTime == null) {
			return "";
		}

		return dateTime.format(DateTimeFormatter.ofPattern(format));
	}

}
