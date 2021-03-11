### 해당 프로젝트에서 얻을 수 있었던 스킬 & 느낀점
=================================================

##### 1. Spring Boot (Framework)
- 기본적인 Annotation 의 개념과 사용법을 배울 수 있음.
- HTTP method를 활용한 Mapping 시 return 값을 통한 static or template 세팅을 학습 가능.
- Model, HttpSession과 같은 파라미터 활용법에 대해 배울 수 있음.
- (다양한 Annotation에 대한 느낀점은 다 쓸 수 없어서 생략.)


##### 2. Swagger (API tools? docs?)
- RESTful API를 강의의 마지막 부분에서 개발하며, 이를 Swagger ui로 API 상태 체크가 가능한 것을 학습 가능.
- 일반 @Controller로 설정된 경로들과 @RestController로 설정된 경로의 차이점을 이해하면 Swagger 활용에 더욱 용이할 것으로 보임.


##### 3. Tomcat (Server)
- 로컬 환경에서는 Spring boot에 내장된 Tomcat 서버를 배울 수 있음.
- 원격 EC2 환경에서는 Tomcat 서버를 직접 설치하여 사용하여 설정을 해볼 수 있음.
- 이 과정은 Tomcat에서 프로젝트를 구동시키는게 어떤 의미인지 이해할 수 있으므로 상당히 유익.


##### 4. Refactoring
- 개발 과정에서 발생할 수 있는 중복코드를 효율적으로 리팩토링 할 수 있음.
- 객체 지향적인 코드를 개발하기 위해 어떤 방식으로 코드를 구성해야 하는지.


##### 5. Mustache (Template format)
- 여러 종류의 템플릿 중 Mustache를 활용하여 html을 구성하는 과정을 배울 수 있음.


##### 6. H2 (Database)
- Spring boot에 내장된 H2를 활용하여 빠른 개발과 테스트가 가능.


##### 7. Maven (Build Tool)
- Build Tool 로써, Gradle에 비해 느리다는 평을 받고는 있으나, 여러 dependency를 추가/삭제 해보는 경험 가능.
- dependency의 버전에 예제를 따라하며 달라지는 부분들에 대한 공부도 가능.
- 실제 build 시, 생성되는 target 프로젝트, jar, war등에 대한 개념과 배포 차이를 이해할 수 있음.


##### 8. Deploy
- 웹 서비스의 기능개발 뿐 아니라 원격 서버의 배포까지 경험 가능.
- LOCAL 환경과 원격 EC2 환경에서의 차이를 체험할 수 있음. (생각보다 많은 이슈가 나타남.)
- 강의의 마지막 부분에 기본적인 수준의 배포 자동화 스크립트의 구성을 체험하는 것은 무척 의미있음.


##### 9. JPA
- DB에 대한 Original한 접근 없이 Java 코드로만 테이블에 대한 CRUD 처리 과정을 배울 수 있음.
- 자유로운 활용을 위해서는 상당한 학습이 필요할 것으로 보임.
- Refactoring 과정에서 JPA의 추상화는 상당히 유용할 것으로 보임.



