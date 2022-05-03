/*
    2022.05.03
    게시글 등록/수정 날짜를 자동으로 관리
    이 클래스를 상속받으면, create Date, modified Date 인식함
 */

package com.kbstar.springboot.study.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*
    31. JPA Auditing
        게시글의 등록/수정 날짜를 자동 관리

        @MappedSuperClass
            JPA Entity Class들이 BaseTimeEntity(현재 클래스) 상속받게 할 예정(abstract class처럼)
            > 상속받으면, 자동으로 createDate, modifiedDate를 칼럼으로 인식하게 함
        @EntityListeners
            BaseTimeEntity 클래스에 Auditing 기능을 포함시키겠다

        ** 이 BaseTimeEntity 클래스를 Posts에서 상속받도록 변경할 예정 **
 */
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseTimeEntity {

    @CreatedDate
//    private LocalDateTime createDate;
    private String createDate;

    @LastModifiedDate
//    private LocalDateTime modifiedDate;
    private String modifiedDate;

    // 해당 엔티티를 저장하기 이전에 먼저(pre) 실행한다
    @PrePersist
    public void onPrePersist()
    {
        this.createDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm"));
        this.modifiedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm"));
    }

    // 해당 엔티티를 수정하기 이전에 실행 (수정이니까 create는 건드리면 안됨)
    @PreUpdate
    public void onPreUpdate()
    {
        this.modifiedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm"));
    }
}
