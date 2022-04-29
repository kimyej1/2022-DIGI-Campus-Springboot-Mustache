package com.kbstar.springboot.study.domain.posts;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


/*
    14. 저장소를 위한 interface
        Posts 클래스로부터 DB 접근이 가능하게 해줄 JpaRepository
        MyBatis : DAO : Data Access Object
            cf) DTO : Data Transfer Object

            JpoRepository<클래스, PrimaryKey>
            -> 이 녀석을 상속받기만 하면, CRUD 메소드가 자동으로 생성됨!!

            Entity 클래스 = Posts
            Entity Repository = PostsRepository
            ****** 이 둘은 왼쪽 트리에서 같은 위치(같은 패키지 내부)에 있어야 됨 ******
 */

// 할 일 : 저장소 처리가 잘 되는지 확인 -> 단위테스트를 통해서!

public interface PostsRepository extends JpaRepository<Posts, Long> { // JpaRepository 상속받으면 얘가 다 해줌

    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();

    @Modifying   // 이 쿼리는 데이터에 영향을 미치는 쿼리야 (insert, update, delete)
    @Query("UPDATE Posts p SET p.view = p.view + 1 WHERE p.id = :id ")
    int updateView(Long id);

    @Modifying
    @Query("UPDATE Posts p SET p.rec = p.rec + 1 WHERE p.id = :id ")
    int updateRec(Long id);

//    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
//    Page<Posts> findAllPage(Pageable pageable);

    /*
        여기서 사용하는 쿼리는 RDB와 약간 차이가 있다.
        *** "JPQL" 나중에 찾아보기! ***
     */

    // ** 검색 전체 목록     -- 얘는 검색만하고, 소팅같은거는 페이지네이션쪽에서 해줌
//    List<Posts> findByTitleContaining(String key);    // SELECT * FROM Posts WHERE title like '%key%' 이거랑 같은 기능

    // ** 검색 정보를 페이지 단위 목록으로
    Page<Posts> findByTitleContaining(String key, Pageable pageable);

}

