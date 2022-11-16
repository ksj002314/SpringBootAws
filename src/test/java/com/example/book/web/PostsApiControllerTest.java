package com.example.book.web;

import com.example.book.domain.posts.Posts;
import com.example.book.domain.posts.PostsRepository;
import com.example.book.web.dto.PostsSaveRequestDto;
import com.example.book.web.dto.PostsUpdateRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostsRepository postsRepository;

    @AfterEach
    public void tearDown() {
      postsRepository.deleteAll();
    }

    @Test
    public void Posts_등록된다() throws Exception {
        String title = "title";
        String content = "content";
        String author = "author";
        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();

        String url = "http://localhost:" + port + "/api/v1/posts";

        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> postsList = postsRepository.findAll();
        assertThat(postsList.get(0).getTitle()).isEqualTo(title);
        assertThat(postsList.get(0).getContent()).isEqualTo(content);
        assertThat(postsList.get(0).getAuthor()).isEqualTo(author);
    }

    @Test
    public void Posts_수정() throws Exception {
        Posts savedPosts = postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());
        Long updateId = savedPosts.getId();
        String expectedTitle = "title2";
        String expectedContent = "content2";

        PostsUpdateRequestDto requestDto = PostsUpdateRequestDto.builder()
                .title(expectedTitle)
                .content(expectedContent)
                .build();

        String url = "http://localhost:" + port + "/api/v1/posts/" + updateId;

        HttpEntity<PostsUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(all.get(0).getContent()).isEqualTo(expectedContent);
    }


}


/*  설명
*   HelloController와 달리 @WebMvcTest를 사용 안함
*   @WebMvcTest의 경우 JPA 기능이 작동하지 않기 때문
*   : Controller와 ControllerAdvice 등 외부 연동과 관련된 부분만 활성화되니 지금 같이 JPA 기능까지 한 번에 테스트할 때는 @SpringBootTest 와 TestRestTemplate을 사용
*
*   RANDOM_PORT를 사용하기 때문에 @SpringBootTest의 기본값인 Mock 웹 서버를 띄우는 것과 다르게 실제로 Tomcat을 띄우고 포트를 랜덤으로 생성해 띄운다.
*   Tomcat은 기본값으로 8080으로 띄워지지만 실제로 배포할 땐 8080이 다른 애플리케이션에서 사용할 수도 있고 악의적인 의도를 가진 사용자가
*   8080번으로 Spring Boot 가 띄워진것을 알고 공격을 할 수 있기 때문에 8080으로 띄우지 않는 경우가 있다.
*
*   따라서 최대한 배포 환경과 비슷하게 배포하기 위해 RANDOM_PORT를 사용하였다.
*
*
* */