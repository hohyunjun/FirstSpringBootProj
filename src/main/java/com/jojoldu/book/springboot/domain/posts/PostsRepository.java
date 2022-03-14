package com.jojoldu.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

// Mybatis 등에서 Dao 로 불리는 DB Layer 접근자.
// JPA 에선 Repository 라고 부르며 인터페이스로 생성함.
// 단순히 인터페이스를 생성 후, JpaRepository<Entity 클래스, PK 타입> 을 상속하면 기본 CRUD 메소드가 자동 생성됨.
// 주의!! Entity 클래스와 기본 Entity Repository 는 함께 위치해야 함.
// Entity 클래스는 기본 Repository 없이는 제대로 역할을 할 수가 없음
// 일반적으로 두개 파일은 domain 패키지에서 함께 관리함.
public interface PostsRepository extends JpaRepository<Posts, Long>{

}
