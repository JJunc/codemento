package com.codemento.board.controller;

import com.codemento.board.dto.PostSaveForm;
import com.codemento.board.service.PostService;
import com.codemento.user.entity.User;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/community")
public class BoardController {

    private final PostService postService;

    @GetMapping
    public String community() {
        return "community/community";
    }

    @GetMapping("create")
    public String postForm(Model model) {
        model.addAttribute("form", new PostSaveForm());
        return "community/post-save-form";
    }

    @PostMapping("create")
    public String createPost(@ModelAttribute PostSaveForm form, HttpSession session) {// 작성자 설정
        postService.save(form, session);
        return "community/community";
    }
}
