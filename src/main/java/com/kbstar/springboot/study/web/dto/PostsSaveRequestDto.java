package com.kbstar.springboot.study.web.dto;

import com.kbstar.springboot.study.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
    18. 게시글 저장을 위한 데이터 덩어리 만들기
        id(idx), title, content, author     -- id는 사실 없이도 처리 가능(자동생성이라서)

         +-----------------------+   +-----------+
         |    Web Layer          |   |           |
         |   (controllers) (3)   |   |    DTO(1) |
         +-----------------------+   |           |
         +-----------------------+   |           |
         |    Service Layer(2)   |   +-----------+
         |                       |   +-----------+
         +-----------------------+   |  Domain   |
         +-----------------------+   |  Model    |
         |    Repository Layer   |   | (Entity)  |
         |        (DB)           |   |여기선 Posts |
         +-----------------------+   +-----------+

         - 만드는 순서 : DTO -> Service Layer -> Controller
         - 흐름도 : 껍데기에서 사용자가 명령 누름 -> Controller가 명령 받음
                  -> Controller이 Service Layer에 DTO 주면서 명령 수행 요청(param : DTO)
                  -> Service가 명령 안에서 Controller으로부터 받은 DTO의 .toEntity()를 수행(param : Entity)
                  -> Entity(Domain Model)단위로 명령 수행하면서 DB(Repository Layer)에 영향

 */
@Getter
@NoArgsConstructor  // default constructor
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author)    // copy constructor
    {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity()
    {
        return Posts.builder().title(title).content(content).author(author).build();
    }
}
