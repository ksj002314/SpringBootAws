package com.example.book.service;

import com.example.book.domain.posts.Posts;
import com.example.book.domain.posts.PostsRepository;
import com.example.book.web.dto.PostsListResponseDto;
import com.example.book.web.dto.PostsResponseDto;
import com.example.book.web.dto.PostsSaveRequestDto;
import com.example.book.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }


    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById (Long id) {
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        return new PostsResponseDto(entity);
    }

    
    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id =" + id ));
        postsRepository.delete(posts);
    }
}

/* 설명
* readOnly
*       트랜잭션 어노테이션( @Transactional ) 의 옵션
*       트랜잭션 범위는 유지하되, 조회 기능만 남겨두어 조회 속도가 개선됨
*       등록, 수정, 삭제 기능이 없는 메소드에 추천
* */

/* .map(PostsListResponseDto::new 
*       = .map(posts -> new PostsListResponseDto(posts))
*       postRepository 결과로 넘어온 Posts의 Stream을 map을 통해 PostsListResponseDto로 변환 -> List로 반환하는 메소드
* */


/* postsRepository.delete(posts); */
/* JpaRepository에서 이미 delete 메소드를 지원하고 있음 */
/* 엔티티를 파라미터로 삭제할 수도 있고, deleteById 메소드를 이용하면 id로 삭제도 가능 */
/* 존재하는 Posts인지 확인을 위해 엔티티 조회 후 그대로 삭제 */