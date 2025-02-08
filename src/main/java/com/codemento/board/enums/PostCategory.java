package com.codemento.board.enums;

import lombok.Getter;
import lombok.Setter;

@Getter
public enum PostCategory {
    JOB("취업"),
    FREE("자유"),
    BACKEND("백엔드"),
    FRONTEND("프론트"),
    NOTICE("공지사항");

    private final String description;

    PostCategory(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
