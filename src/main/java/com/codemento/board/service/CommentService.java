package com.codemento.board.service;

import com.codemento.board.dto.comment.CommentListDto;
import com.codemento.board.dto.comment.CommentSaveDto;
import com.codemento.board.dto.mapper.CommentMapper;
import com.codemento.board.entity.Comment;
import com.codemento.board.repository.CommentRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    public void saveComment(CommentSaveDto commentSaveDto) {
//        Comment comment = commentMapper.saveToEntity(commentSaveDto);
//        if (commentSaveDto.getParentCommentId() == null) {
//            comment.setParentComment();
//        }
//        commentRepository.save();
    }

    public List<CommentListDto> findAllCommentsByPostId(Long postId) {
        return commentRepository.findByPostId(postId)
                .stream()
                .map(commentMapper::toListDto) // ✅ 자동 변환
                .collect(Collectors.toList());
    }
}
