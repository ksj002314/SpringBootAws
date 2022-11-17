package com.example.book.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {
}


/*  @Target(ElementType.PARAMETER)
*   어노테이션이 생성될 수 있는 위치를 지정한다.
*   PARAMETER - 파라미터로 선언된 객체에서 사용 가능
*   이 외에도 클래스 선언문에 쓸 수 있는 TYPE 등이 있다. */

/*  Retention(RetentionPolicy.RUNTIME)
*   커스텀 어노테이션을 생성할 때 주로 사용
*   RetentionPolicy의 값을 넘겨주는 것으로 어노테이션의 메모리 보유 범위가 결정 */

    /*  RUNTIME
    *   어노테이션을 런타임시에까지 사용할 수 있다.
    *   JVM이 자바 바이트코드가 담긴 class 파일에서 런타임환경을 구성하고 런타임을 종료할 때까지 메모리는 살아있다. */

/*  @Interface
*   어노테이션 클래스로 지정한다는 어노테이션 */