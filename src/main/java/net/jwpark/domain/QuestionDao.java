package net.jwpark.domain;

import org.springframework.data.jpa.repository.JpaRepository;

// DB와의 작업을 담당할 repository
public interface QuestionDao extends JpaRepository<Question, Long>{
	
}
