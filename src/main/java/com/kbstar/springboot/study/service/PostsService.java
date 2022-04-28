package com.kbstar.springboot.study.service;

import com.kbstar.springboot.study.domain.posts.Posts;
import com.kbstar.springboot.study.domain.posts.PostsRepository;
import com.kbstar.springboot.study.web.dto.PostsListResponseDto;
import com.kbstar.springboot.study.web.dto.PostsResponseDto;
import com.kbstar.springboot.study.web.dto.PostsSaveRequestDto;
import com.kbstar.springboot.study.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

//import javax.transaction.Transactional;
import org.springframework.transaction.annotation.Transactional;
// 위에꺼보다 밑에꺼가 성능을 조금 개선 (SELECT를 readonly(데이터에 영향X)로)

import java.util.List;
import java.util.stream.Collectors;


/*
    19. Service 등록
        자동으로 만들어지는 생성자
            public PostsService(PostsRepository postsRepository) { this.postsRepository = postsRepository }
        Transaction : All or Nothing  -- 다 되든지, 아무것도 안되든지 (일부 성공/일부 실패는 없음)
 */
@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto)
    // void 해도 되는데, 결과를 보고싶어서 리턴값 Long(Key value : id(idx))으로 지정
    {
        System.out.println("------------------------- Post Service");
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    /*
        25. 업데이트를 위한 매핑 (객체랑 DB)

            등록 : /api/v1/posts      <---- 새글 등록
            수정 : /api/v1/posts/3    <---- 3번 글을 수정

            1) DTO ---> Service ---> Controller
    */
    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) // id 줄테니까 그 인덱스에 있는거 업데이트해줘
    {
        Posts posts = postsRepository.findById(id).orElseThrow(             // id로 찾아라
                ()-> new IllegalArgumentException("No id for Post : " + id) // 못찾으면~ 이거 해라
        );

        posts.update(requestDto.getTitle(), requestDto.getContent());  // 두개 주면서 업데이트해달라는 명령
        return id;
    }

    @Transactional
    public int updateView(Long id)
    {
        return postsRepository.updateView(id);
    }

    @Transactional
    public void delete(Long id)
    {
        Posts posts = postsRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("No id for Post : " + id)
        );

        postsRepository.delete(posts);
    }

    public PostsResponseDto findById(Long id)
    {
        Posts posts = postsRepository.findById(id).orElseThrow(             // id로 찾아라
                ()-> new IllegalArgumentException("No id for Post : " + id) // 못찾으면~ 이거 해라
        );

        return new PostsResponseDto(posts);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc()
    {
        return postsRepository
                .findAllDesc()
                .stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    /*
        .map(PostListResponseDto::new)
        = .map(posts -> new PostListResponseDto(posts)) // 람다식
     */
}
