package com.codemento.board.dto.comment;

import com.codemento.board.entity.Comment;
import com.codemento.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CommentSaveDto {

    private Long postId;
    private String content;
    private String authorId;
    private Long parentCommentId;

}
