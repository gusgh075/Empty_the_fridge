package com.ohgiraffers.ingredientstockservice.ingredientstock.command.application.dto.response;


import com.ohgiraffers.ingredientstockservice.ingredientstock.command.domain.aggregate.DisposalHistory;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@Builder
public class DisposalCreateResponse {
    private final DisposalHistory disposalHistory;
}
