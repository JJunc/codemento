package com.codemento.config;

import com.codemento.board.enums.converter.StringToPostCategoryConverter;
import com.codemento.user.interceptor.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // 인터셉터를 등록
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor())
                .addPathPatterns("/user/mypage") // 로그인 상태 체크할 URL
                .excludePathPatterns("/user/login", "/user/signup", "/"); // 로그인/회원가입 페이지는 제외
    }

    private final StringToPostCategoryConverter stringToPostCategoryConverter;

    public WebConfig(StringToPostCategoryConverter stringToPostCategoryConverter) {
        this.stringToPostCategoryConverter = stringToPostCategoryConverter;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(stringToPostCategoryConverter);
    }

}