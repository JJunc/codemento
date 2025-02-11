package com.codemento.user.dto;

import com.codemento.user.entity.User;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserLoginForm {

    @NotBlank(message = "아이디를 입력하세요")
    private String id;

    @NotBlank(message = "비밀번호를 입력하세요")
    private String password;

    public User toEntity() {
        return User.builder()
                .id(id)
                .password(password).
                build();
    }
}
