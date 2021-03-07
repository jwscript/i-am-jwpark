package net.jwpark.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> { // JpaRepository의 어떤 클래스에 대한 것인지 첫번째 파라미터, PK는 두번째 파라미터의 타입
	
	// PK가 아닌 userId로 데이터를 조회해오기 위해 선언.
	User findByUserId(String userId);
}
