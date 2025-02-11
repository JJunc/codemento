package com.codemento.user.enums;

public enum UserRole {
    MENTOR("멘토"),
    USER("일반회원"),
    ADMIN("관리자");

    private final String roleName; // 역할 이름을 저장할 필드 추가

    UserRole(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }
}