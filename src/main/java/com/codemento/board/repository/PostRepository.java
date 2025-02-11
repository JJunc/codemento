package com.codemento.board.repository;

import com.codemento.board.entity.Post;
import com.codemento.board.enums.PostCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    // 모든 게시글을 페이징 처리하기
    Page<Post> findAll(Pageable pageable);

    // 제목으로 게시글 조회 (페이징 처리)
    Page<Post> findByTitle(String title, Pageable pageable);

    // 저자 이름으로 게시글 조회 (페이징 처리)
    Page<Post> findByAuthor(String author, Pageable pageable);

    // 카테고리별 게시글 조회 (페이징 처리)
    Page<Post> findByPostCategory(PostCategory category, Pageable pageable);

}
