package com.codemento.board.controller;


import com.codemento.board.dto.comment.CommentListDto;
import com.codemento.board.dto.comment.CommentSaveDto;
import com.codemento.board.service.CommentService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("create")
    public String createComment(@ModelAttribute CommentSaveDto commentSaveDto,
    @RequestParam("postId") Long postId, HttpSession session) {
        commentSaveDto.setPostId(postId);
        commentSaveDto.setAuthorId((String) session.getAttribute("userId"));
        commentService.saveComment(commentSaveDto);
        return "redirect:/community/" + postId;
    }

}
