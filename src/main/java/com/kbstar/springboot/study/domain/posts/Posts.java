package com.kbstar.springboot.study.domain.posts;

import com.kbstar.springboot.study.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
/*
    13. 게시글 관련 클래스 정의
        <form method='get' enctype='multiport/form-data' action='a.jsp'> // enctype 저거 안쓰면 파일이 이름만 날아가고 사이즈, 이런 속성은 안날라감
            <input type='file' name='upfile'>
        </form>
 */

/*
    <Annotation>
        @Entity : JPA에서 필요한 annotation : 테이블과 클래스를 Mapping
            Posts.java -> posts 데이터베이스 테이블 매핑
            MyPosts.java -> my_port 테이블을 만든다
                myFamilyCount : camel 표기법
                my_family_count : snake 표기법 -> 내부적으로 이미 얘가 쓰고있어서, 겹치거나 하면 오류날수도 있으니 카멜로 쓸것!
        @Id : 데이터베이스의 키 값
        @GeneratedValue : 키 생성
        @Column : 데이터베이스 테이블을 내부적으로 생성해줄 때 사이즈 등 속성 설정
 */

// 할 일 : 저장소를 위한 PostsRepository 생성해야 한다.
// class : PostsRepository.java (interface)

/*
    31. JPA Auditing을 위해 BaseTimeEntity 클래스를 상속받는다. (extends BaseTimeEntity)
        Application.java : 시작하는 부분 -> 여기에 JPA Auditing 활성화시키는 Annotation 추가해야함
 */

@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity {

    @Id                     // key value
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;        // idx 같은 역할 : 게시글의 id

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable=false)
    private String content;

    @Column(length = 100, nullable=false)
    private String author;

    @Column(columnDefinition = "integer default 0", nullable = false)
    private int view;

    @Column(columnDefinition = "integer default 0", nullable = false)
    private int rec; // 추천하기

    // Copy Constructor
    @Builder
    public Posts(String title, String content, String author)
    {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    /*
        24. 데이터 지속성(Consistent : 객체와 Entity의 일치)
            객체가 업데이트되면 자동으로 DB 데이터가 변경

            실제 DB에 업데이트되도록 만들기
            DTO 작업 끝났다 --> Service로 가서 작업 (실제 DB에 업데이트하는 코드)
     */
   public void update(String title, String content)
    {
        this.title = title;
        this.content = content;
    }
}