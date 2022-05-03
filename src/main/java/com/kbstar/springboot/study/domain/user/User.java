/*
    2022.05.03
    테이블과 클래스 매핑하는 Entity (객체 업데이트되면 자동으로 DB에 반영하려고)
 */

package com.kbstar.springboot.study.domain.user;

import com.kbstar.springboot.study.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String picture;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(String name, String email, String picture, Role role)   // constructor
    {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    public User update(String name, String picture)
    {
        this.name = name;
        this.picture = picture;
        return this;
    }

    public String getRoleKey()
    {
        // ROLE_GUEST 이런거 리턴해주는 함수
        return this.role.getKey();
    }
}
