package com.codemento.user.dto;

import com.codemento.user.entity.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class UserSignInForm {

    @NotBlank(message = "아이디를 입력하세요")
    private String id;

    @NotBlank(message = "닉네임을 입력하세요")
    @Size(min = 2, max = 10, message="닉네임은 2~10 글자 사이여야 합니다.")
    private String nickname;

    @NotBlank(message = "비밀번호를 입력하세요")
    @Size(min = 8, max = 16, message="비밀번호는 최소 8글자 최대 16글자 이상이여야 합니다.")
    private String password;

    @NotBlank
    private String email;

    @NotBlank
    private String phoneNumber;

    private String githubProfile;
    private String linkedInProfile;
    private LocalDateTime createdDate;


    public User toEntity() {
        return User.builder()
                .id(id)
                .nickname(nickname)
                .email(email)
                .password(password)
                .phoneNumber(phoneNumber)
                .githubProfile(githubProfile)
                .createdDate(LocalDateTime.now())
                .linkedInProfile(linkedInProfile)
                .build();
    }
}
