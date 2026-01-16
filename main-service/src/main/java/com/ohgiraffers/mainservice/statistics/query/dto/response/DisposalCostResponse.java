package com.ohgiraffers.recipeservice.statistics.query.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor // 모든 필드 초기화하는 생성자 자동 생성
public class DisposalCostResponse {
    private final Long totalDisposalCost; // 총 폐기 비용
}