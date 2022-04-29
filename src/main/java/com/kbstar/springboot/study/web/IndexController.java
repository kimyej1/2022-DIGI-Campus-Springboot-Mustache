package com.kbstar.springboot.study.web;

import com.kbstar.springboot.study.service.PostsService;
import com.kbstar.springboot.study.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import javax.persistence.Id;

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

    @GetMapping("/")
    public String index(Model model, @PageableDefault(sort="id", direction = Sort.Direction.DESC, size = 2) Pageable pageable)    // 이 model이라는 형식에 맞춰서 줘라
    {
//        model.addAttribute("posts", postsService.findAllDesc());                  // 얘는 100개 있으면 100개 다가져오는애
        // postsService의 findAllDesc를 index.mustache 에 {{#posts}} 형식으로 맞춰서~~

        model.addAttribute("posts", postsService.pageList(pageable));  // 얘는 페이지별로 한페이지에 두개씩 가져오기
        model.addAttribute("prev", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());

//        model.addAttribute("hasPrev", pageable.hasPrevious());
//        model.addAttribute("hasNext", pageable.hasNext());
        return "index";
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
