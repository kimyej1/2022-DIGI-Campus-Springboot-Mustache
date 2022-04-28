package com.kbstar.springboot.study.web;

import com.kbstar.springboot.study.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// REST vs. 이전 http://localhost/main.php?cmd=test&idx=3
// http://localhost/hello 이런식으로 뒤에 따라붙는 추가정보 없이 개발하고싶어 -> REST
@RestController     // RESTful API

public class HelloController
{   // com.kbstar.springboot.study.Application.java가 main이므로, 얘는 main 아님

    @GetMapping("/hello")     // Method 중 get 방식으로 오는 애들을 mapping 해줄래
    // JSON(JavaScript Objective Notation)을 매핑해준다.
    // Method 종류 : [GET / POST / PUT / DELETE] -> GetMapping, PostMapping, ...
    //              * SpringBoot : POST(insert)와 PUT(update)을 구분한다.

    /*
        [HTTP Error Code]
        1xx : Trying                                    / 180 Ringing
        2xx : OK                                        / 200 OK -- 메시지 이거 하나밖에 없음
        3xx : Temporary Error, Redirection Error
        4xx : Permanent Error, Client Error             / 403 Forbidden  404 Not Found  405 Method Not Allowed
        5xx : Server Error
        6xx : Global Error
     */

    public String hello()
    {
        return "hello";
    }

    /*
        8. http://localhost:8080/hello/dto?name=홍길동&age=12
            http://localhost:8080/hello/dto/?name=홍길동&age=12
     */

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name,
                                     @RequestParam("age") int age)
    {
        return new HelloResponseDto(name, age);
    }
}
