package com.example.book.config.auth;

import com.example.book.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOauth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                    .authorizeRequests()
                    .antMatchers("/","/css/**","/images/**","/js/**","/h2-console/**").permitAll()
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                    .anyRequest().authenticated()
                .and()
                    .logout()
                        .logoutSuccessUrl("/")
                .and()
                    .oauth2Login()
                        .userInfoEndpoint()
                            .userService(customOauth2UserService);
    }
}


/*  @EnableWebSecurity : Spring Security 설정 활성화 */
/*  .csrf().disable().headers().frameOptions().disable() : h2-console화면을 사용하기 위해 해당 옵션들을 disable */
/*  .authorizeRequests() : URL 별 권한 관리를 설정하는 옵션의 시작점 , authorizeRequests가 선언되어야만 antMatchers 옵션을 사용 할 수 있다. */
/*  .antMatchers("/api/v1/**) : 권한 관리 대상을 지정하는 옵션, URL HTTP 메소드 별로 관리 가능 , "/"등 지정된 URL들은 premitAll() 옵션을 통해 전체 열람 권한을 주었다. "/api/v1/**" 줏소를 가진 API는 USER권한을 가진 사람만 가능하도록 하였다.  */
/*  .anyRequest() : 설정도니 값들 이외 나머지 URL들을 나타낸다.  여기서는 authenticated()를 추가하여 나머지 URL들은 모두 이늦ㅇ된 사용자들에게만 허용(로그인한 사용자) */
/*  .logout().logoutSuccessUrl("/") : 로그아웃 기능에 대한 여러 설정의 진입점. 로그아웃 성공시 / 주소로 이동  */
/*  .oauth2Login() : oAuth2 로그인 기능에 대한 여러 설정의 진입점 */
/*  .userInfoEndpoint() : OAuth2 로그인 성공 이후 사용자 정보를 가져올 때의 설정들을 담당 */
/*  .userService(customOAuth2UserService) : 소셜 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스의 구현체를 등록 */
/*                                          리소스 서버(소셜 서비스)에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능을 명시할 수 있다. */