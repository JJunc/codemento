package com.codemento.board.service;

import com.codemento.board.dto.mapper.PostMapper;
import com.codemento.board.dto.post.PostDetailDto;
import com.codemento.board.dto.post.PostListDto;
import com.codemento.board.dto.post.PostRequestDto;
import com.codemento.board.entity.Post;
import com.codemento.board.enums.PostCategory;
import com.codemento.board.repository.PostRepository;
import com.codemento.user.entity.User;
import com.codemento.user.service.UserService;
import com.codemento.util.SessionManager;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final SessionManager sessionManager;

    public void save(PostRequestDto dto) {
        dto.setContent(Jsoup.parse(dto.getContent()).text());
        postRepository.save(postMapper.requestDtoToEntity(dto));
    }

    @Transactional
    public void update(PostRequestDto dto) {
        Post post = postRepository.findById(dto.getId())
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        post.setTitle(dto.getTitle());   // 값 변경
        post.setContent(Jsoup.parse(dto.getContent()).text()); // 값 변경
    }

    @Transactional
    public void delete(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        post.setIsDeleted("Y");
    }

    public PostDetailDto findById(Long id) {
        Optional<Post> post = postRepository.findById(id);
        return postMapper.toPostDetailDto(post.get());
    }


    public Page<Post> getPostsByTitle(String title, int page, int size) {
        // PageRequest.of(page, size)로 페이지 번호와 크기를 설정
        Pageable pageable = PageRequest.of(page, size);
        return postRepository.findByTitle(title, pageable);
    }

    public Page<Post> getPostsByAuthor(String author, int page, int size) {
        // 페이지 번호와 크기 설정
        Pageable pageable = PageRequest.of(page, size);
        return postRepository.findByAuthor(author, pageable);
    }


    // 카테고리별 게시글 목록을 페이징 처리
    public Page<PostListDto> getPostsByCategory(PostCategory category, int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("createdDate").descending());  // 페이지 번호는 0부터 시작
        Page<Post> posts = postRepository.findByPostCategory(category, pageable);

        return posts.map(post -> new PostListDto(
                post.getId(),
                post.getTitle(),
                post.getAuthor().getId(),
                post.getAuthor().getNickname(),
                post.getIsDeleted(),
                post.getViews(),
                post.getCreatedDate(),
                post.getPostCategory()));
    }

    // 전체 게시글 목록을 페이징 처리
    public Page<PostListDto> getAllPosts(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("createdDate").descending());
        Page<Post> posts = postRepository.findAll(pageable);

        return posts.map(post -> postMapper.toPostListDto(post));
    }

}

