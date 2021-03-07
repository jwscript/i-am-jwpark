package net.jwpark;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class MyWebInitializer extends SpringBootServletInitializer {
	
	// 톰캣 서버가 동작하면서 configure 메서드를 호출한다.
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(IAmJwparkApplication.class); // 초기화를 IAmJwparkApplication class를 통해서 진행하겠다는 것.
	}
}
