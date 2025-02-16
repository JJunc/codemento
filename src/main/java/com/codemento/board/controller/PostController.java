package com.codemento.board.controller;

import com.codemento.board.dto.comment.CommentListDto;
import com.codemento.board.dto.comment.CommentSaveDto;
import com.codemento.board.dto.post.PostDetailDto;
import com.codemento.board.dto.post.PostListDto;
import com.codemento.board.dto.post.PostRequestDto;
import com.codemento.board.enums.PostCategory;
import com.codemento.board.service.CommentService;
import com.codemento.board.service.PostService;
import com.codemento.util.SessionManager;
import groovy.util.logging.Slf4j;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@lombok.extern.slf4j.Slf4j
@Controller
@RequestMapping("/community")
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;
    private final CommentService commentService;
    private final SessionManager sessionManager;

    @GetMapping
    public String getCommunityPosts(
            @RequestParam(defaultValue = "ALL") PostCategory category,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            Model model) {

        Page<PostListDto> postPage;

        // 카테고리별 또는 전체 게시글 조회
        if (PostCategory.ALL.equals(category)) {
            postPage = postService.getAllPosts(page, size);  // 전체 게시글
        } else {
            postPage = postService.getPostsByCategory(category, page, size);  // 카테고리별 게시글
        }

        model.addAttribute("posts", postPage.getContent());
        model.addAttribute("totalPages", postPage.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("category", category);

        // 글이 없을 경우 페이징 버튼을 비활성화/숨기기 위한 처리
        model.addAttribute("isEmpty", postPage.isEmpty());

        return "community/community";
    }


    @GetMapping("/post/create")
    public String postForm(Model model) {
        model.addAttribute("form", new PostRequestDto());
        return "community/post-save-form";
    }

    @PostMapping("/post/create")
    public String createPost(@ModelAttribute PostRequestDto form, HttpSession session) {// 작성자 설정
        form.setAuthorId(sessionManager.getLoginUserId(session));
        postService.save(form);
        return "redirect:/community";
    }

    @GetMapping("/{postId}")
    public String postDetail(@PathVariable(name = "postId") Long postId, Model model) {
        PostDetailDto post = postService.findById(postId);
        List<CommentListDto> commentList = commentService.findAllCommentsByPostId(postId);
        model.addAttribute("post", post);
        model.addAttribute("comments", commentList);
        model.addAttribute("commentSaveForm", new CommentSaveDto());
        return "community/post-detail";
    }

    @GetMapping("/post/edit")
    public String postUpdateForm(@RequestParam(name = "postId") Long postId, Model model) {
        model.addAttribute("post", postService.findById(postId));
        return "community/post-update";
    }

    @PostMapping("/post/edit")
    public String postUpdate(@ModelAttribute PostRequestDto dto) {
        postService.update(dto);
        return "redirect:/community/" + dto.getId();
    }

    @PostMapping("/post/delete")
    public String postDelete(@RequestParam(name = "postId") Long postId) {
        postService.delete(postId);
        return "redirect:/community";
    }


}
