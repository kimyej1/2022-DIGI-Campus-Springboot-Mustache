package com.kbstar.springboot.study;

// 이클립스의 ctrl + shift + O = alt + enter (안해도 자동임포트 되는듯..?)
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// ANNOTATION....
/*
    32. JPA Auditing을 위한 Annotation을 추가하면 끝! (@EnableJpaAuditing)
        -> 다시 실행시킨 후 h2-console 들어가서 필드 추가 확인
            <단위테스트> PostsRepositoryTest에 다시 넣고, 검색하는 기능을 확인!

        내부적인 동작: save, update, delete, select 는 기본적으로 만들어진 상태
        -> 확인을 위해 HTML로 처리..

    mustache 라는 패키지 설치(마스코트 : 콧수염) : 매우 간단한 템플릿 엔진
        command + shift + A > plugins > mustache install
        -> mustache를 이 프로그램이 인식하게 만들기 -> build.gradle

    <작업을 위한 디렉토리(폴더) 구조>
        src/main/resources/templates/index.mustache(html 파일) : 전에 이미지 한폴더로 몰아두는것처럼 리소스에 모아두기
 */
@EnableJpaAuditing
@SpringBootApplication // 1. SpringBoot를 자동설정하겠다. : 여기서부터 설정을 읽는다(진입점이 여기다!) -> 항상 프로젝트 최상단에 위치
public class Application {
    public static void main(String[] args)
    {
        SpringApplication.run(Application.class, args); // 웹서버(내장 WAS : TomCat) 실행 명령 -> main()에서 시작 위치
    }
}

// 2. 설정 바꾸기
//      Preferences > Editor > General > Code Completion > Match case 해제 > Apply
//      JSP : MVC Model ( Model, View, Controller)


/*
    스프링부트의 큰 장점 : 단위테스트가 가능하다
        - 전체 컴파일을 다시 하지 않고 test폴더에 있는 java로 지금 수정한 부분만 (단위테스트) 가능



    10. http://localhost:8080/hello/dto/?name=홍길동&age=78

    11. JPA : Java Persistence API : 자바 지속성 API
            클래스 <-> DB 자동 Mapping
                - 기존 방식 : 프로그래밍의 대부분이 Query
                - ORM : Object Relational Mapping -> SQL의 종속성에서 벗어나게 하겠다.
            CRUD : Create, Read, Update, Delete
            조회 : Member member = jpa.find(memberID)

            JPA 장점 : 수정이 간단하다 (-> 유지보수가 간단하다), 객체(Class)가 변경되면, 알아서 DB에 업데이트 쿼리가 날라간다.

    +--------------------------+
    |  Java Application        |
    |  +--------------------+  |
    |  | JPA                |  |
    |  |  +--------------+  |  |           +----------+
    |  |  |   JDBC API   |- |- |----SQL--->|   DB     |
    |  |  |              |<-|- |---------- |          |
    |  |  +--------------+  |  |           + ---------+
    |  +--------------------+  |
    +-------------------------+
 */