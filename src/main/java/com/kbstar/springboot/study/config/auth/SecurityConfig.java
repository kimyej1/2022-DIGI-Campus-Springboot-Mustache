/*
    2022.05.03
    시큐리티 관련 설정들 모아둠

    (예) "/api/vi/**" 주소는 최소 Role-Guest 권한을 가진 사람만 접근 가능
        로그아웃 기능 설정 및 성공 시 메인("/")으로 이동하도록 지정
 */

package com.kbstar.springboot.study.config.auth;


import com.kbstar.springboot.study.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable().headers()
                .frameOptions().disable()
                .and()
                .authorizeRequests()
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
                .antMatchers("/api/v1/**").hasRole(Role.GUEST.name()) // ** : all -> default: guest(로그인만 하면 다 쓸수있게)
                .anyRequest().authenticated()
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .oauth2Login()
                .userInfoEndpoint()
                .userService(customOAuth2UserService);
    }
}