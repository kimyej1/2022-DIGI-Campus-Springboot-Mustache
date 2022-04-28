package com.kbstar.springboot.study.web.dto;

import com.kbstar.springboot.study.domain.posts.Posts;
import lombok.Getter;

import java.time.LocalDateTime;

/*
    PostsResponseDto는 한개씩만 주니까
    여러개 한번에 반환하는 Dto 만들어서 메인에 목록 보여주려고
 */
@Getter
public class PostsListResponseDto {
    private Long id;
    private String title;
//    private String content; -- 목록에 콘텐츠는 필요없어
    private String author;
    private String modifiedDate;
    private int view;
    private int rec;

    public PostsListResponseDto(Posts entity)
    {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.author = entity.getAuthor();
        this.modifiedDate = entity.getModifiedDate();
        this.view = entity.getView();
        this.rec = entity.getRec();
    }

}
