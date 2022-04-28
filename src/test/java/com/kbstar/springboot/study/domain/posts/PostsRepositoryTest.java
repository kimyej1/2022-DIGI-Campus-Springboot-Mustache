package com.kbstar.springboot.study.domain.posts;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/*
    16. Annotation
        @AfterEach : 전에는 @After : 각 단위테스트가 끝날 때마다 수행해야하는 작업 정의
            테스트가 DB 추가 -> 실제 데이터에 영향을 미칠 수 있다 ==> 테스트가 실제 데이터에 영향을 주지 않도록 처리하려고 사용!
            in-memory DB : H2DB
            postsRepository.save()      // insert, update 둘 중 하나를 수행 (키값 없으면 인서트, 있으면 해당 값 업데이트)
            postsRepository.findAll()   // Select * -> 결과를 List Collection에 add()
 */

// 현재 테스트 성공했는데, 내부적으로 어떤 일을 수행하는지 확인해보고싶음 -> 쿼리 출력해서!
// main/resources/application.properties 파일을 만들어서 셋팅 //  내부적인 속성정보를 여기에 기록할거야

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @AfterEach
    public void cleanup()
    {
        postsRepository.deleteAll();
    }

    @Test
    public void saveAndLost()
    {
        String title = "테스트 제목";
        String content = "테스트 본문";

        postsRepository.save( Posts.builder().title(title).content(content).author("user@kbstar.com").build() );

        List<Posts> postsList = postsRepository.findAll();

        Posts posts = postsList.get(0);
        System.out.println("----------------------------- title = " + posts.getTitle());
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void baseTimeEntityTest()    // 글쓴 시간, 수정된 시간이 들어가는지 확인 (시간이 중요!)
    {
        LocalDateTime now = LocalDateTime.of(2022, 4, 25, 0, 0, 0);
        postsRepository.save( Posts.builder().title("kb title").content("kb content").author("user@kbstar.com").build() );

        List<Posts> postsList = postsRepository.findAll();
        Posts posts = postsList.get(0);

        System.out.println("----------------------------- createDate : " + posts.getCreateDate());
        System.out.println("----------------------------- modifiedDate : " + posts.getModifiedDate());

        // 사실 제목, 콘텐츠는 확인 안해도 됨..
        assertThat(posts.getTitle()).isEqualTo("kb title");
        assertThat(posts.getContent()).isEqualTo("kb content");

        // 날짜는 확인!!
//        assertThat(posts.getCreateDate()).isAfter(now); // 현재 시간 이후의 데이터냐?
//        assertThat(posts.getModifiedDate()).isAfter(now);
    }
}

/*
    15. 단축키
        (mac) syso + ctrl + space = sout + tab = System.out.println();
 */
