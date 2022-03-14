package com.jojoldu.book.springboot.domain.posts;

import com.jojoldu.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter // Lombok Annotation - 클래스 내 모든 필드의 Getter 메소드 자동 생성
@NoArgsConstructor // Lombok Annotation - 기본 생성자 자동 추가 public Posts(){} 와 같은 효과
@Entity // JPA Annotation - 주요 어노테이션 : 주요 어노테이션을 클래스에 가깝게 배치
// Entity 어노테이션은 테이블과 링크될 클래스임을 나타냄.
public class Posts extends BaseTimeEntity { // 실제 DB의 Table 과 매칭될 클래스. Entity 클래스

    @Id // 테이블의 PK 필드
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK 의 생성 규칙
    private Long id;

    // 테이블 컬럼 정의 - 굳이 어노테이션 선언하지 않더라도 해당 클래스의 필드는 모두 칼럼이 됨.
    // 기본값 외에 추가로 변경이 필요한 옵션이 있을 경우 사용함(사이즈, 타입 등)
    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder // 해당 클래스의 빌드 패턴 클래스 생성
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }

}
