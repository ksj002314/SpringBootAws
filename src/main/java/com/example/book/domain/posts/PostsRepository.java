package com.example.book.domain.posts;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts,Long> {
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}

/*  해당 클래스
*   Posts 클래스로 Database를 접근하게 해 줄 JpaRepository를 생성
*
*   보통 ibatis나 Mybatis 등에서 Dao라고 불리는 DB Layer 접근자
*   JPA에선 Repository라고 부르며 인터페이스로 생성
*   단순히 인터페이스를 생성 후, JpaRepository<Entity 클래스, PK 타입>을 상속하면 기본적인 CRUD 메서드가 자동 생성됨
*
*   주의 : Entity 클래스와 기본 Entity Repository는 함께 위치해야 함   => Entity 클래스는 기본 Repository 없이닌 제대로 역할을 할 수 없음
*
*   나중에 프로젝트 규모가 커져 도메인별로 프로젝트를 분리해야 한다면, Entity 클래스와 기본 Repository는 함께 움직여야 하므로 도메인 패키지에서 함께 관리
* */

/* @Query(쿼리문) */
/* SpringDataJpa에서 제공하지 않는 메소드는 위처럼 쿼리로 작성해도 된다. */