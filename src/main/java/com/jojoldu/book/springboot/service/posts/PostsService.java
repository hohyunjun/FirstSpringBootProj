package com.jojoldu.book.springboot.service.posts;

import com.jojoldu.book.springboot.domain.posts.Posts;
import com.jojoldu.book.springboot.domain.posts.PostsRepository;
import com.jojoldu.book.springboot.web.dto.PostsListResponseDto;
import com.jojoldu.book.springboot.web.dto.PostsResponseDto;
import com.jojoldu.book.springboot.web.dto.PostsSaveRequestDto;
import com.jojoldu.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.stream.Collectors;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        /* DB 에 UPDATE 쿼리 날리지 않아도 되는 이유 - JPA Dirty Checking
        *  JPA 의 영속성 컨텍스트( 엔티티 영구 저장 환경 )
        *  트랜잭션 안에서 데이터베이스의 데이터를 가져오면, 이 데이터는 영속성 컨텍스트가 유지된 상태임
        *  이 상태에서 해당 데이터의 값을 변경하면, 트랜잭션이 끝나는 시점에 해당 테이블에 변경분을 반영함
        *  즉, Entity 객체의 값만 변경하면 별도로 UPDATE 쿼리를 날릴 필요가 없는 것
        *  이 개념을 Dirty Checking 이라고 함
        * */
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    @Transactional
    public void delete(Long id){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        postsRepository.delete(posts); // JpaRepository 에서 이미 delete 메소드를 지원함
    }

    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }

    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc(){
        // postsRepository 결과로 넘어온 Posts의 Stream 을 map 을 통해 PostsListDto 로 변환 후 List 로 반환함
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }
}
