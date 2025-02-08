package com.codemento.board.entity;

import com.codemento.board.enums.PostCategory;
import com.codemento.member.entity.User;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private String content;
    private String isDeleted;

    @Enumerated(EnumType.STRING)
    private PostCategory category;

    @OneToMany(mappedBy = "post" , cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    @ManyToOne
    @JoinColumn(name = "post_author_id")
    private User author;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)  // 생성일은 수정되지 않도록
    private LocalDateTime createdDate;

    @UpdateTimestamp
    @Column(nullable = false)  // 수정일은 반드시 존재
    private LocalDateTime updatedDate;
}
