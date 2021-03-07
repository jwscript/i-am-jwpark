package net.jwpark.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

// User Class가 DB와 연결되는 것이라는 의미의 어노테이션 Entity.
@Entity
public class User {
	// Id: PK 설정용 어노테이션, GeneratedValue: 가장 최근에 추가된 값에서 자동으로 1씩 증가.
	@Id
	@GeneratedValue
	private Long seq;

	// 이 컬럼에는 null 값이 허용되지 않음을 설정.
	@Column(nullable = false, length = 20)
	private String userId;

	private String password;
	private String name;
	private String email;

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return this.password;
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
