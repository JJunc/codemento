package com.codemento.board.entity;

import com.codemento.user.entity.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String isDeleted;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @ManyToOne
    @JoinColumn(name = "comment_author_id")
    private User author;

    @Column(nullable = false)
    @Lob
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

    @Builder
    public Comment(Long id, String isDeleted, Post post, User author, String content,
                   Comment parentComment, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.id=id;
        this.isDeleted = isDeleted;
        this.post = post;
        this.author = author;
        this.content = content;
        this.parentComment = parentComment;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}
