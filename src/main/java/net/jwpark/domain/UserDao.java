package net.jwpark.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> { // JpaRepository의 어떤 클래스에 대한 것인지 첫번째 파라미터, PK는 두번째 파라미터의 타입
	

}
