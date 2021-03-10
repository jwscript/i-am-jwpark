package net.jwpark;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableJpaAuditing // 엔티티 변경 시점에 대한 정보를 기록할 수 있게 해주는어노테이션.
@EnableSwagger2
public class IAmJwparkApplication {

	public static void main(String[] args) {
		SpringApplication.run(IAmJwparkApplication.class, args);
	}

	@Bean
	public Docket commonApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("i-am-jwpark").apiInfo(this.apiInfo()).select()
				.paths(PathSelectors.ant("/api/**")) // url이 '/api/'로 시작하는 경우에 스웨거에 노출되도록 함.
				.build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("I Am Jwpark - API Title").description("I Am Jwpark - API Description")
				.build();
	}
	
	/**
	 * TODO: 현재 swagger 는 2.9.2 버전을 쓰고 있는데, 3.0.0 버전으로 올리면 에러가 발생함.
	 * 아마 사용법이 바뀐것 같은데.. 이 부분 언젠가 테스트 해보기.
	 */
}
