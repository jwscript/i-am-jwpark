package net.jwpark;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // 엔티티 변경 시점에 대한 정보를 기록할 수 있게 해주는어노테이션.
public class IAmJwparkApplication {

	public static void main(String[] args) {
		SpringApplication.run(IAmJwparkApplication.class, args);
	}

}
