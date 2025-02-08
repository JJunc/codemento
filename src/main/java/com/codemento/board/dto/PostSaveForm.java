package com.codemento.board.dto;

import com.codemento.board.entity.Post;
import com.codemento.board.enums.PostCategory;
import com.codemento.member.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class PostSaveForm {

    private String title;
    private String content;
    private User author;
    private PostCategory category;
    private LocalDateTime createdDate;

    @Builder
    public PostSaveForm(String title, String content, User author, PostCategory category, LocalDateTime createdDate) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.category = category;
        this.createdDate = createdDate;
    }

    public Post toEntity() {
        return Post.builder()
                .author(author)
                .title(title)
                .content(content)
                .postCategory(category)
                .build();
    }
}
