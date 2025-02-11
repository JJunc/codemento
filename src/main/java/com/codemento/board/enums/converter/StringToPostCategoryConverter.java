package com.codemento.board.enums.converter;

import com.codemento.board.enums.PostCategory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToPostCategoryConverter implements Converter<String, PostCategory> {

    @Override
    public PostCategory convert(String source) {
        // source 값이 null이거나 빈 문자열일 경우 예외 처리
        if (source == null || source.isEmpty()) {
            return null;
        }

        // 문자열을 enum 타입으로 변환
        try {
            return PostCategory.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;  // 잘못된 값에 대한 예외 처리
        }
    }
}
