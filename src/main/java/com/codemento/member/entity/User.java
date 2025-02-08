package com.codemento.member.entity;

import com.codemento.member.enums.MemberRole;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
public class User {

    @Id
    private Long id;

    private String isDeleted;

    private String name;
    private String password;
    private String email;
    private String phone;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)  // 생성일은 수정되지 않도록
    private LocalDateTime createdDate;

    @UpdateTimestamp
    @Column(nullable = false)  // 수정일은 반드시 존재
    private LocalDateTime updatedDate;

    @Enumerated(EnumType.STRING)
    private MemberRole memberRole;

    private String githubProfile;    // GitHub 프로필 URL
    private String linkedInProfile;  // LinkedIn 프로필 URL
}
