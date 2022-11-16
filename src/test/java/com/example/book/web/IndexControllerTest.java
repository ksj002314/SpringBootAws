package com.example.book.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IndexControllerTest {
    
    @Autowired
    private TestRestTemplate restTemplate;
    
    @Test
    void 메인페이지_로딩() {
        String body = this.restTemplate.getForObject("/",String.class);
        
        assertThat(body).contains("스프링 부트로 시작하는 웹 서비스");
    }
}
/*  설명
*   TestRestTemplate : Spring Boot에서 컨트롤러를 테스트하기 위해서 사용
* 
*   기존의 MockMvc도 컨트롤러를 테스트하는 라이브러리인데 무슨 차이가 있을까?
*       서블릿 컨테이너의 실행 여부
*           MockMvc : 컨테이너를 실행하지 않는다.
*           TestRestTemplate : 컨테이너를 직접 실행시킨다.
*       테스트의 관점의 차이
*           MockMvc : 서버의 입장에서 구현한 API를 통해 비지니스 로직에 문제가 없는지 테스트
*           TestRestTemplate : 클라이언트 입장에서 사용할 때 문제가 없는지 테스트
* */