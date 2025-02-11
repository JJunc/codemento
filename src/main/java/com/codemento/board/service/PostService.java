package com.codemento.board.service;

import com.codemento.board.dto.PostListDto;
import com.codemento.board.dto.PostSaveForm;
import com.codemento.board.entity.Post;
import com.codemento.board.enums.PostCategory;
import com.codemento.board.repository.PostRepository;
import com.codemento.user.entity.User;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public void save(PostSaveForm form, HttpSession session) {
        User loginUser = User.builder()
                .id((String) session.getAttribute("userId"))
                .build();
        form.setAuthor(loginUser);
        postRepository.save(form.toEntity());
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
                post.getAuthor(),
                post.getViews(),
                post.getCreatedDate(),
                post.getPostCategory()));
    }

    // 전체 게시글 목록을 페이징 처리
    public Page<PostListDto> getAllPosts(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size, Sort.by("createdDate").descending());
        Page<Post> posts = postRepository.findAll(pageable);

        return posts.map(post -> new PostListDto(
                post.getId(),
                post.getTitle(),
                post.getAuthor(),
                post.getViews(),
                post.getCreatedDate(),
                post.getPostCategory()));
    }
}

