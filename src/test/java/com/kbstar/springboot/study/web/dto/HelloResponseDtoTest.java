package com.kbstar.springboot.study.web.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/*
    6. Lombok getter 자동으로 동작하는지 단위테스트
 */
public class HelloResponseDtoTest {

    @Test
    public void lombokTest()
    {
        String name = "test";
        int age = 34;

        HelloResponseDto dto = new HelloResponseDto(name, age);
        // 생성자 없으니까 원래 그런거 없다고 에러나야하는데 정상동작한다면 자동으로 만들어진게 있다는 뜻!
        assertThat(dto.getName()).isEqualTo(name);   // assert : 표명하다
        assertThat(dto.getAge()).isEqualTo(age);

    }
}
