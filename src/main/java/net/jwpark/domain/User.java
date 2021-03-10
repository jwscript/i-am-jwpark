package net.jwpark.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

// User Class가 DB와 연결되는 것이라는 의미의 어노테이션 Entity.
@Entity
public class User extends AbstractEntity {
	// 이 컬럼에는 null 값이 허용되지 않음을 설정.
	@Column(nullable = false, length = 20)
	@JsonProperty
	private String userId;

	@JsonIgnore
	private String password;

	@JsonProperty // 이 프로퍼티를 Json으로 변환하겠다는 것.
	private String name;

	@JsonProperty
	private String email;

	public boolean matchSeq(Long newSeq) {
		if (newSeq == null) {
			return false;
		}

		return newSeq.equals(super.getSeq());
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean matchPassword(String newPassword) {
		if (newPassword == null) {
			return false;
		}

		return newPassword.equals(password);
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + password + ", name=" + name + ", email=" + email + "]";
	}

	public void update(User updateUser) {
		this.password = updateUser.password;
		this.name = updateUser.name;
		this.email = updateUser.email;

	}
}
