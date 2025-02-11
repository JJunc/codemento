package com.codemento.user.controller;

import com.codemento.user.dto.UserLoginForm;
import com.codemento.user.dto.UserSignInForm;
import com.codemento.user.entity.User;
import com.codemento.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("signIn")
    public String signInForm(Model model) {
        model.addAttribute("form", new UserSignInForm());
        return "user/signin-form";
    }

    @PostMapping("signIn")
    public String signIn(@Valid @ModelAttribute("form") UserSignInForm form, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "user/signin-form";
        }

        userService.saveUser(form);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("form", new UserLoginForm());
        return "user/login-form";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("form") UserLoginForm form,
                        BindingResult bindingResult,
                        HttpServletRequest request,
                        Model model) {
        // 입력값 검증
        if (bindingResult.hasErrors()) {
            return "user/login-form"; // 검증 오류 시 로그인 페이지 유지
        }

        UserLoginForm loginUser = userService.login(form);

        if (loginUser != null && loginUser.getPassword().equals(form.getPassword())) {
            HttpSession session = request.getSession(); // 세션 생성

            // 세션에 사용자 ID 및 필요한 최소 정보만 저장
            session.setAttribute("userId", loginUser.getId()); // 사용자 ID 저장

            return "redirect:/"; // 로그인 성공 후 홈으로 리다이렉트
        } else {
            model.addAttribute("error", "아이디 또는 비밀번호가 틀렸습니다.");
            return "/user/login-form";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate(); // 세션 삭제
        }
        return "redirect:/";
    }




    // 닉네임 중복 검증
    @GetMapping("/checkNickname")
    @ResponseBody
    public Map<String, Boolean> checkNickname(@RequestParam String nickname) {
        boolean exists = userService.isNicknameDuplicate(nickname);
        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", exists);
        return response;
    }

    // 이메일 중복 검증
    @GetMapping("/checkEmail")
    @ResponseBody
    public Map<String, Boolean> checkEmail(@RequestParam String email) {
        boolean exists = userService.isEmailDuplicate(email);
        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", exists);
        return response;
    }

    // 아이디 중복 검증
    @GetMapping("/checkUserId")
    @ResponseBody
    public Map<String, Boolean> checkUserId(@RequestParam String userId) {
        boolean exists = userService.isUserIdDuplicate(userId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", exists);
        return response;
    }
}
