package com.codemento.board.dto.post;

import com.codemento.board.dto.comment.CommentListDto;
import com.codemento.board.entity.Comment;
import com.codemento.board.entity.Post;
import com.codemento.board.enums.PostCategory;
import com.codemento.user.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class PostDetailDto {

    private Long id;
    private String title;
    private String content;
    private int views;
    private String string;
    private String authorId;
    private String authorNickname;
    private PostCategory postCategory;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private List<Comment> comments;


}
