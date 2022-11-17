package com.example.book.web;

import com.example.book.config.auth.LoginUser;
import com.example.book.config.auth.dto.SessionUser;
import com.example.book.service.PostsService;
import com.example.book.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class indexController {

    private final PostsService postsService;
    private final HttpSession httpSession;

    @GetMapping("/posts/save")
    public String postSave() {
        return "posts-save";
    }


    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }


    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());
        //SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if(user != null) {
            model.addAttribute("userName", user.getName());
        }
        return "index";
    }
}

/*  (SessionUser)httpSession.getAttribute("user")   : 앞서 작성된 CustomOAuth2UserService에서 로그인 성공 시 세션에 Sessionuser를 저장하도록 구성
*                                                     즉, 로그인 성공 시 httpSession.getAttribute("user")에서 값을 가져올 수 있다. */

/*  if(user != null) : 세션에 저장된 값이 있을 경우에는 model에 userName으로 등록한다.
*                       저장된 값이 없을 경우 model엔 아무 값도 없으므로 로그인 버튼이 보이게 된다. */


/* @LoginUser SessionUser user
*   기존에 (SessionUer)httpSession.getAttribute("user") 로 가져오던 세션 정보 값이 개선되었다.
*   이제 어느 컨트롤러인지 @LoginUser만 사용하면 세션 정보를 가져올 수 있게 되었다. */