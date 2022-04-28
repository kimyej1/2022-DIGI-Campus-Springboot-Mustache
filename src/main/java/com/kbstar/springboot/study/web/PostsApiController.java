package com.kbstar.springboot.study.web;

import com.kbstar.springboot.study.service.PostsService;
import com.kbstar.springboot.study.web.dto.PostsResponseDto;
import com.kbstar.springboot.study.web.dto.PostsSaveRequestDto;
import com.kbstar.springboot.study.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/*
    20. 작업 순서

    Controller -------DTO-------> Service 저장 요청 -------> DB


    <form method="post" action="/api/v1/posts">     // action: 주소창에 보여지는 부분 (GET은 주소창에 나오지만 POST는 안나오니까)
        <input type="text" name="title">
        <textarea name="content"></textarea>        // input/textarea: 실제 POST로 날라가는 데이터
        <button type="submit"> SAVE </button>
    </form>
 */

@RequiredArgsConstructor    // final 변수들만으로 생성자 만들어주는것
/*
    자바의 final과는 다른 의미
        - 자바 final : read only variable   (예) private final int PI = 3.14;
        - 스프링부트 final : copy constructor 에 넣을 variable    (변수가 여러개인데 생성자에 넣고싶은 애들은 정해져있으니까)
 */
@RestController
public class PostsApiController {                       // 1) Controller가
    // Service 먼저 만들어야함

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")   // 맨위 주석의 action="api/v1/posts"처럼 이렇게 날라가게 하면 됨
    public Long save(@RequestBody PostsSaveRequestDto requestDto)    // 2) DTO를 주면서 서비스를 요청
    {
        System.out.println("------------------------- Controller -> Service");
        return postsService.save(requestDto);           // 3) Service한테 save 해달라고
    }

    /*
        26. 수정을 위한 REST 등록
            Method : POST, GET, *** PUT ***, DELETE

            DTO -> Service -> Controller -> 단위테스트
     */
    @PutMapping("/api/v1/posts/{id}")  // Get으로 받으면 id번째 글 보여달라는거, Put으로 받으면 수정해달라는거 (메소드로 구분!)
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto)
    {
        return postsService.update(id, requestDto);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id)
    {
        postsService.delete(id);
        return id;
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id)
    {
        return postsService.findById(id);
    }
}
