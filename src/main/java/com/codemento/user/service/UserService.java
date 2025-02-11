package com.codemento.user.service;

import com.codemento.user.dto.UserLoginForm;
import com.codemento.user.dto.UserSignInForm;
import com.codemento.user.entity.User;
import com.codemento.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void saveUser(UserSignInForm form) {
        userRepository.save(form.toEntity());
    }

    public UserLoginForm login (UserLoginForm form) {
        userRepository.findById(form.getId())
                .filter(m -> m.getPassword().equals(form.getPassword()))
                .orElse(null);
        UserLoginForm loginForm = new UserLoginForm();
        loginForm.setId(form.getId());
        loginForm.setPassword(form.getPassword());
        return loginForm;
    }

    // 중복 닉네임 검증
    public boolean isNicknameDuplicate(String nickname) {
        return userRepository.findByNickname(nickname).isPresent();
    }

    // 중복 이메일 검증
    public boolean isEmailDuplicate(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    // 중복 아이디 검증
    public boolean isUserIdDuplicate(String userId) {
        return userRepository.findById(userId).isPresent();
    }
}
