package com.codemento.util;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;

@Component
public class SessionManager {

    private static final String USER_ID = "userId";

    /**
     * 세션에서 사용자 ID 가져오기
     */
    public String getLoginUserId(HttpSession session) {
        String userId = (String) session.getAttribute(USER_ID);
        if (userId == null) {
            throw new IllegalStateException("로그인이 필요합니다.");
        }
        return userId;
    }

    /**
     * 세션에 사용자 ID 저장
     */
    public void login(HttpSession session, String userId) {
        session.setAttribute(USER_ID, userId);
    }

    /**
     * 세션에서 사용자 로그아웃
     */
    public void logout(HttpSession session) {
        session.invalidate();
    }
}
