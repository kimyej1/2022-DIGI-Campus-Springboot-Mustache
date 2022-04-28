package com.kbstar.springboot.study.web;

import com.kbstar.springboot.study.web.HelloController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

// 3. 아래 import는 처음 한번 직접 타이핑(alt + enter)해서 없는 녀석 불러올거야
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@ExtendWith(SpringExtension.class)              // 이전에는 @RunWith
@WebMvcTest(controllers= HelloController.class) // Model View Controller - @Controller

/*
    ******** 전체 구조는 main 구조와 같아야한다. ********
        디렉토리 구조(com.kbstar.springboot.study.web) 포함..
 */
public class HelloControllerTest {

    @Autowired  // 자동주입: field(변수), constructor(생성자), setter 에 사용
    private MockMvc mvc;

    @Test
    public void helloReturnTest() throws Exception
    {
        String hello = "hello";

        mvc.perform(get("/hello"))          // 주소창에 "/hello" 가 get 방식으로 붙어 왔다고 생각하고 테스트해봐
                .andExpect(status().isOk())            // 그랬을 때 200번(OK)메시지가 올 거라고 예상하고 있어
                .andExpect(content().string(hello));   // 그랬을 때 컨텐츠에는 string hello 가 리턴될것같아
                                                       // 실제 그렇게 되는지 확인해줘~!
    }

    @Test
    public void helloDtoTest() throws Exception
    {
        String name = "HongKilDong";
        int age = 78;

        mvc.perform(get("/hello/dto")
                        .param("name", name)    // helloReturnTest는 두번째줄이 괄호닫고 .이었지만, 얘는 괄호 안에 .이라서 들여쓰기 다르게함
                        .param("age", String.valueOf(age)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.age", is(age)));

    }
}

/*
    4. Lombok 설치 (Lombok : 생성자, getter/setter을 자동으로 처리)

        앞으로 할 일
        1) cmd + shift + A > Plugins > Lombok 설치 확인
        2) build.gradle 에 library 추가
 */