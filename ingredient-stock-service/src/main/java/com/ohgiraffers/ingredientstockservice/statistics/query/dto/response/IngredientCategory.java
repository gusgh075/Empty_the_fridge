package com.ohgiraffers.ingredientstockservice.statistics.query.dto.response;

import lombok.Getter;

@Getter
public enum IngredientCategory {
    
    // ⚠️ 중요: DB에 저장된 값과 정확히 일치해야 마이바티스가 알아서 찾아줍니다. (소문자 유지)
    produce("농산물"),
    livestock("축산물"),
    seafood("수산물"),
    processed("가공식품");

    private final String description;

    IngredientCategory(String description) {
        this.description = description;
    }
}