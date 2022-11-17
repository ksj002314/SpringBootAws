package com.example.book.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}

/* findByEmail : 소셜 로그인으로 반환되는 값 중 email을 통해 이미 생성된 사용자인지 처음 가입하는 사용자인지 판단하기 위한 메소드이다. */
/* Optional<T> : null이 올 수 있는 값을 감싸는 Wrapper클래스로 참조하더라도 NullPointerException 이 발생하지 않도록 도와준다. */