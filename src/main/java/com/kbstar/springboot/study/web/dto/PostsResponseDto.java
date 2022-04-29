package com.kbstar.springboot.study.web.dto;

import com.kbstar.springboot.study.domain.posts.Posts;
import lombok.Getter;
/*
    23. Request에 대한 응답 데이터 만들기

        Controller
                        DTO
        Service
                        Entity
        Repository
        : Entity를 이용해 DTO 만들기
 */
@Getter
public class PostsResponseDto {
    private Long id;
    private String title;
    private String content;
    private String author;
    private int view;
    private int rec;

    public PostsResponseDto(Posts entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent(); //.replace("\n", "<br />"); // <br> 단독태그라 그냥 해도 되는데, 여러 언어에도 호환되게하려면 안전하게 <br />
        this.author = entity.getAuthor();
        this.view = entity.getView();
        this.rec = entity.getRec();
    }
}