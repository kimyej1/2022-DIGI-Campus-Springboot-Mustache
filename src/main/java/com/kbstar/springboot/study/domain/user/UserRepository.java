/*
    2022.05.03
    User로부터 DB 접근 (CRUD : Create Read Update Delete 메소드 자동 생성)
    findByEmail()로 이미 DB에 생성되어있는 사용자인지 확인할 수 있음
 */

package com.kbstar.springboot.study.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
}
