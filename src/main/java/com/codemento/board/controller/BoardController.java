package com.codemento.board.controller;

import com.codemento.board.dto.PostListDto;
import com.codemento.board.dto.PostSaveForm;
import com.codemento.board.enums.PostCategory;
import com.codemento.board.service.PostService;
import com.codemento.user.entity.User;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/community")
public class BoardController {

    private final PostService postService;

    @Controller
    @RequiredArgsConstructor
    public class PostController {

        private final PostService postService;

        @GetMapping("/community")
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
    }


    @GetMapping("create")
    public String postForm(Model model) {
        model.addAttribute("form", new PostSaveForm());
        return "community/post-save-form";
    }

    @PostMapping("create")
    public String createPost(@ModelAttribute PostSaveForm form, HttpSession session) {// 작성자 설정
        postService.save(form, session);
        return "redirect:/community";
    }
}
