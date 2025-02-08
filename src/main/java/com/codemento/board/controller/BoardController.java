package com.codemento.board.controller;

import com.codemento.board.dto.PostSaveForm;
import com.codemento.board.service.PostService;
import com.codemento.member.entity.User;
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
        return "community/post-save";
    }

    @PostMapping("create")
    public String createPost(@ModelAttribute PostSaveForm form, HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser"); // 세션에서 로그인한 사용자 가져오기
        form.setAuthor(loginUser); // 작성자 설정
        postService.save(form);
        return "community/community";
    }
}
