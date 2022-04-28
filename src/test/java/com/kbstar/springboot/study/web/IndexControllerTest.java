package com.kbstar.springboot.study.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat; // assertThat 빨간줄나오면 이거 적기(static으로)

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // 테스트하면서 본프로그램 죽일 수 없으니, 테스트에는 랜덤 포트번호를 사용할게~!
public class IndexControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void loadingMainPage()
    {
        String body = this.restTemplate.getForObject("/", String.class);
        assertThat(body).contains("index.mustache");

        /*
            단위테스트 OK 되면, Application 다시 실행해서
            "http://localhost:8080/" 만 치고 들어갔을때 'index.mustache'로 들어가는지 확인!
         */
    }
}
