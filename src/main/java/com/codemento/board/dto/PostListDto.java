package com.codemento.board.dto;

import com.codemento.board.entity.Post;
import com.codemento.board.enums.PostCategory;
import com.codemento.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class PostListDto {

    private Long id;
    private String title;
    private User author;
    private int views;
    private LocalDateTime createdDate;
    private PostCategory postCategory;


    public Post toEntity() {
        return Post.builder()
                .id(id)
                .postCategory(postCategory)
                .title(title)
                .author(author)
                .views(views)
                .build();
    }
}
