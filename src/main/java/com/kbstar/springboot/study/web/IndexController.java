package com.kbstar.springboot.study.web;

import com.kbstar.springboot.study.config.auth.LoginUser;
import com.kbstar.springboot.study.config.auth.dto.SessionUser;
import com.kbstar.springboot.study.domain.posts.Posts;
import com.kbstar.springboot.study.service.PostsService;
import com.kbstar.springboot.study.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import javax.mail.Session;
import javax.persistence.Id;
import javax.servlet.http.HttpSession;
import java.util.List;

/*
    34. IndexController.java
        http://kbstar.com/ 이렇게 들어오면 (query가 없으면)
        -> index를 리턴한다. (index.mustache 파일이 있으면, 이 파일이 index 파일을 대체한다.)


    header, tail 분리해서 구조를 단순화시키기

 */
@RequiredArgsConstructor // copy constructor
@Controller
public class IndexController {

//    @GetMapping("/")
//    public String index()
//    {
//        return "index"; // 인덱스 페이지로 가! 하는 기능
//    }

    private final PostsService postsService;
    /*
        public IndexController(PostsService postsService)
        {
            this.postsService = postsService;
        }
     */

    // Google Login
//    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model,    // 이 model이라는 형식에 맞춰서 줘라
                        @PageableDefault(sort="id", direction = Sort.Direction.DESC, size = 3) Pageable pageable,
                        @LoginUser SessionUser user) // Google Login - HttpSession 주석처리하고 이거 넣었음
    {
        Page<Posts> pageList = postsService.pageList(pageable);

//        model.addAttribute("posts", postsService.findAllDesc());                  // 얘는 100개 있으면 100개 다가져오는애
        // postsService의 findAllDesc를 index.mustache 에 {{#posts}} 형식으로 맞춰서 js에 날려줘

        model.addAttribute("posts", pageList);  // 페이지별로 한페이지에 두개씩 가져오기
        model.addAttribute("prev", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());

//        model.addAttribute("hasPrev", pageable.hasPrevious());
//        model.addAttribute("hasNext", pageable.hasNext());

        model.addAttribute("hasPrev", pageList.hasPrevious());  // T/F
        model.addAttribute("hasNext", pageList.hasNext());

        // Google Login
//        SessionUser user = (SessionUser) httpSession.getAttribute("user");
        if(user != null)
        {
            model.addAttribute("userName", user.getName());
        }

        return "index";
    }

    @GetMapping("/posts/search")
    public String search(String keyword, Model model, @PageableDefault(sort="id", direction = Sort.Direction.DESC, size = 3) Pageable pageable)
    {
        Page<Posts> searchList = postsService.search(keyword, pageable);

        model.addAttribute("searchList", searchList);  // 페이지별로 한페이지에 두개씩 가져오기
        model.addAttribute("prev", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("hasPrev", searchList.hasPrevious());  // T/F
        model.addAttribute("hasNext", searchList.hasNext());
        model.addAttribute("keyword", keyword);

        return "posts-search"; // posts-search.mustache로 가라
    }

    @GetMapping("/posts/printWrite")
    public String postWrite()
    {
        return "posts-print-write";
    }

    @GetMapping("/posts/show/{id}")
    public String postShow(@PathVariable Long id, Model model)
    {
        // 읽은 카운트 먼저 증가시키고, 읽어오기
        postsService.updateView(id);                        // 카운트 증가

        PostsResponseDto dto = postsService.findById(id);   // 읽어오기
        model.addAttribute("posts", dto);
        return "posts-show";
    }

    @GetMapping("/posts/update/{id}")
    public String postUpdate(@PathVariable Long id, Model model)
    {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("posts", dto);
        return "posts-print-update";
    }
}
