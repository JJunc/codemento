package com.codemento.board.dto.post;

import com.codemento.board.entity.Post;
import com.codemento.board.enums.PostCategory;
import com.codemento.user.entity.User;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostRequestDto {

    private Long id;
    private String title;
    private String content;
    private String authorId;
    private String authorNickname;
    private PostCategory postCategory;
    private LocalDateTime createdDate;

}
