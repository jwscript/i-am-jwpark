--------------------[참고 사항]-------------------------------------------------------------
------------------------------------------------------------------------------------------

1. liveReload 기능을 쓰려면 반드시 devtools가 설치되어야 한다.

2. Controller에서 return 되는 String은 templates 아래의 파일명임.

3. 메모리 데이터베이스 H2에 테이블 추가시, Schema Export가 정상적으로 되지 않는다면,
ㄴ application.yaml > spring.jpa.hibernate.ddl-auto:update 처리.

4. 실서버에서 jar 실행 시, mustache 템플릿에서 경로 이슈가 발생하는 경우, jar 실행이 아닌, maven 프로젝트 실행으로 우회.
ㄴ 'Terminal 명령 7번' 참고.

5. import.sql을 class path 밑에 생성하면 프로젝트 빌드 시, 해당 명령을 수행한다.

6. jar 배포
모바일에서는 web 자원을 관리하지 않고 데이터만 웹 서버단에서 제공.

7. war 배포
웹에서는 image, html과 같은 자원이 있어서 jar가 아닌 war로 배포하는게 좋다.
참고로 spring-boot에 톰캣이 내장되어 있으나,
dependency에 톰캣의 scope=provided 을 추가하여 내장된 톰캣을 사용하지 않고 별도의 톰캣을 사용하겠다고 지정한다.
이 경우, 톰캣을 내장하기는 하였으나 원격서버에서 쓰기 위해서는 톰캣을 일단 다운로드 받아야 함.
그리고, 톰캣의 webapps가 실행될 웹 애플리케이션 들이 있는 곳임. 여기서 ROOT 삭제 후, 우리 프로젝트를 기본값으로 지정해준다.

8. Tomcat 설치
ㄴ 1) 톰캣 설치
ㄴ 2) 프로젝트를 올릴 경로는 webapps에 올리면 되며, 이때 기본값 (/)으로 매핑된 디렉토리는 webapps/ROOT 이다.
(webapps 경로에 들어가보면 하나의 톰캣 서버에 여러개의 애플리케이션이 올라와있는 것을 볼 수 있다.)
ㄴ 3) war로 빌드한 프로젝트를 ROOT 대신 집어넣으면 됨.
(참고로 톰캣이 실행되더라도 서버에 바로 반영되지 않고 시간이 걸림.)

--------------------[STS 단축 키]-----------------------------------------------------------
------------------------------------------------------------------------------------------
1. Auto Import : Command + Shift + O

2. Search Type (=class) : Command + Shift + T

3. Search Resource (html, js, css etc..) : Commant + Shift + R

4. 모든 탭 닫기 : Command + Shift + W

5. 열린 탭 목록 : Command + E

6. 메서드 추적 : Control + Command + H

7. 자동완성 : Control + Command + Space

--------------------[Terminal 명령]--------------------------------------------------------
------------------------------------------------------------------------------------------

1. 소스 땡기기
>> git pull

2. 소스 복제
>> git clone {소스가 있는 경로 URL}

3. 메이븐 빌드
>> ./mvnw clean package
clean : target 디렉토리 삭제
package : 빌드하면서 target 디렉토리 최신화해서 생성.

4. 자바로 시작하는 프로세스 찾기.
>> ps -ef | grep java

5. 프로세스 종료.
>> kill -9 {PID 번호}
ㄴ 강제 종료
>> kill -15 {PID 번호}
ㄴ 안전하게 종료 

6. jar 실행
>> java -jar {jar파일명} &
ㄴ 맨 뒤의 &는 백그라운드에서 실행하는 명령.

7. maven 프로젝트 실행
>> ./mvnw spring-boot:run &
ㄴ jar로 압축하지 않고 컴파일 후에 서버를 실행해준다.

8. ssh 실행
>> ssh -i {PEM 키} {Login ID}@{DOMAIN}

9. 포트 체크
>> sudo lost -iTCP:LISTEN -n -P

10. Tomcat 로그 확인 (Spring console에 뜨던 것과 동일)
>> cd ~/tomcat/logs
>> tail -500f catalina.out

--------------------[Application YAML]--------------------------------------------------------
------------------------------------------------------------------------------------------
1. spring.mustache.expose-session-attributes
true : Session에 담긴 데이터를 모델에 담아서 템플릿 엔진에 전달해주는 설정.

2. spring.mvc.hiddenmethod.filter.enabled
true : spring boot 버전업으로 form 태그 하위 input의 _method 추가 방식이 기본값이 false가 되었음.

3. spring.jpa.show-sql
true : 실행할 때, 쿼리 보여주기.