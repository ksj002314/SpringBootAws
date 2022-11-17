package com.example.book.config.auth.dto;

import com.example.book.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}

/*  SessionUser에는 인증된 사용자의 정보만 필요하기 때문에 name, email, picture만 필드로 선언한다. */