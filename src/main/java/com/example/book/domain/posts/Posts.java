package com.example.book.domain.posts;

import com.example.book.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Posts extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 500, nullable = false)
    private String title;
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }


    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

}

/*  해당 클래스 설명

*   Entity 클래스에서는 Setter 메소드를 만들지 않는다. 대신 해당 필드의 값 변경이 필요하면 명확히 그 목적과 의도를 나타낼 수 있는 메소드를 추가해야 한다.
*
*   그러면 Setter가 없는데 어떻게 값을 채워서 DB에 삽입해야 할까?
*   기본적인 구조 : 생성자를 통해 최종 값을 채운 후 DB에 삽입
*   값 변경이 필요한 경우 : 해당 이벤트에 맞는 public 메소드를 호출하여 변경
*
*   Posts 클래스에서는 생성자 대신 @Builder를 통해 제공되는 빌더 클래스를 사용
*   생성자나 빌더나 생성 시점에 값을 채워주는 역할은 똑같다. 다만, 생성자의 경우 지금 채워야 할 필드가 무엇인지 명확히 지정할 수가 없다.
*
* */