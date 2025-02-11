package com.codemento.user.entity;

import com.codemento.user.enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    private String id;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phoneNumber;

    private String isDeleted;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)  // 생성일은 수정되지 않도록
    private LocalDateTime createdDate;

    @UpdateTimestamp
    @Column(nullable = false)  // 수정일은 반드시 존재
    private LocalDateTime updatedDate;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    private String githubProfile;    // GitHub 프로필 URL
    private String linkedInProfile;  // LinkedIn 프로필 URL


    @Builder
    public User(String id, String nickname, String password, String email, String phoneNumber, String githubProfile, String linkedInProfile, LocalDateTime createdDate) {
        this.id = id;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.createdDate = createdDate;
        this.githubProfile = githubProfile;
        this.linkedInProfile = linkedInProfile;
    }
}
