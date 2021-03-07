package net.jwpark.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// User Class가 DB와 연결되는 것이라는 의미의 어노테이션 Entity.
@Entity
public class User {
	// Id: PK 설정용 어노테이션, GeneratedValue: 가장 최근에 추가된 값에서 자동으로 1씩 증가. (이때, 자동 생성 전략은
	// 4가지가 있지만 그 중 IDENTITY를 사용하였음.)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long seq;

	// 이 컬럼에는 null 값이 허용되지 않음을 설정.
	@Column(nullable = false, length = 20)
	private String userId;

	private String password;
	private String name;
	private String email;
	
	public boolean matchSeq(Long newSeq) {
		if (newSeq == null) {
			return false;
		}
		
		return newSeq.equals(seq);
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
