/*
    2022.05.03
    User의 한 항목으로 사용할 Enum (회원 등급 구분해서 나중에 권한 나눌 수 있음)
 */

package com.kbstar.springboot.study.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    // ADMIN, USER, GUEST
    // ROLE..
    GUEST("ROLE_GUEST", "손님"),
    USER("ROLE_USER", "회원");

    private final String key;
    private final String title;
}

/*
    ENUM : (1) ENUMerate, (2) tElephone NUMber

    (1) 열거형
        SUN=1, MON, TUE, WED -> SUN=1 이니까 자동으로 MON=2, TUE=3, WED=4
        AuthError=401, Forbidden=403, NotFound, MethodNotAllowed -> 자동으로 NotFound=404, MethodNotAllowed=405
 */
