/*
    2022.05.03
    로그인과 관련된 코드가 반복적으로 들어가서 점점 지저분해지므로,
    따로 하나로 모아두고, Controller에서 가져다 쓸 때는 단순히 파라미터로 추가해서 사용할 수 있도록 만듬
 */

package com.kbstar.springboot.study.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
    Session을 사용
        로그인과 관련된 코드가 반복적으로 들어가 점점 지저분해진다.
        메소드 인자를 통해서 세션값을 아무데서나 바로 얻을 수 있도록 처리!
 */
@Target(ElementType.PARAMETER)      // 생성될 수 있는 위치를 파라미터로 지정 (메소드의 파라미터로 선언된 객체에서만 사용 가능)
@Retention(RetentionPolicy.RUNTIME) //
public @interface LoginUser {
/*
    @interface : 이 파일을 annotation 클래스로 지정하겠다.
                --> LoginUser 라는 이름을 갖는 어노테이션이 새롭게 생성되었다.
        --> 이렇게 하면 IndexController 에서 update, show, write 등등 메소드에서 나중에 로그인 정보가 필요하더라도
            단순히 파라미터로 추가함으로써 받아올 수 있음!(IndexController index 메소드처럼..)
 */
}
