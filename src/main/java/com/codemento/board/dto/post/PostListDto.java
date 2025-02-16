package com.codemento.board.dto.post;

import com.codemento.board.entity.Post;
import com.codemento.board.enums.PostCategory;
import com.codemento.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostListDto {

    private Long id;
    private String title;
    private String authorId;
    private String authorNickname;
    private String isDeleted;
    private int views;
    private LocalDateTime createdDate;
    private PostCategory postCategory;

}
