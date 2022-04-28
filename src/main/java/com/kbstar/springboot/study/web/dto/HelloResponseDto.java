package com.kbstar.springboot.study.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/*
    5. DTO : Data Transfer Object  -- 프로세스 간 데이터를 전달하는 객체

        @getter : field의 get method를 자동으로 생성
        @requiredArgsConstructor : 선언되어있는 "final" field가 포함된 생성자를 자동으로 생성

            public HelloResponseDto(String name, int Age) {
                this.name = name;
                this.age = age;
            };
            public String getName() { return this.name; }
            public int getAge() { return this.age; }

        * Lombok getter가 자동으로 생성 -> 단위테스트로 확인 -> main, test 구조가 같아야함
 */
@Getter
@RequiredArgsConstructor    // copy constructor : args가 있는 constructor

public class HelloResponseDto {

    private final String name;
    private final int age;

}

/*
    1) DTO 부터 만들어야됨  -- 데이터 전달 객체
        // HelloResponseDto
    2) Service Layer 만듬  -- 데이터 주고받는 루틴 (명령 받을 부분부터 만들어야 명령할 수 있음)
        // PostsService
    3) Web Layer(Controller) 만듬  -- 명령하는 부분 (가장 프론트에서 save 누르면 웹레이어가 DTO를 서비스레이어에 주면서 명령 수행하라고 지시)
        // HelloController
 */