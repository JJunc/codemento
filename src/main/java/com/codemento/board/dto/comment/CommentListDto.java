package com.codemento.board.dto.comment;

import com.codemento.board.entity.Comment;
import com.codemento.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class CommentListDto {

    private String content;
    private String authorId;
    private String authorNickname;
    private Long parentCommentId;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;




}
