package com.codemento.board.entity;

import com.codemento.member.entity.User;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String isDeleted;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @ManyToOne
    @JoinColumn(name = "comment_author_id")
    private User author;

    private String content;

    @ManyToOne
    @JoinColumn(name = "parent_comment_id")
    private Comment parentComment;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)  // 생성일은 수정되지 않도록
    private LocalDateTime createdDate;

    @UpdateTimestamp
    @Column(nullable = false)  // 수정일은 반드시 존재
    private LocalDateTime updatedDate;
}
