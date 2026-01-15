package com.ohgiraffers.mainservice.ingredientstock.command.application.dto.response;


import com.ohgiraffers.mainservice.ingredientstock.command.domain.aggregate.DisposalHistory;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class DisposalCreateResponse {
    private final DisposalHistory disposalHistory;
}
