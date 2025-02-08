package com.codemento.board.service;

import com.codemento.board.dto.PostSaveForm;
import com.codemento.board.entity.Post;
import com.codemento.board.enums.PostCategory;
import com.codemento.board.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public void save(PostSaveForm postSaveForm) {
        postRepository.save(postSaveForm.toEntity());
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

    public Page<Post> getPostsByCategory(PostCategory category, int page, int size) {
        // 페이지 번호와 크기 설정
        Pageable pageable = PageRequest.of(page, size);
        return postRepository.findByPostCategory(category, pageable);
    }
}

