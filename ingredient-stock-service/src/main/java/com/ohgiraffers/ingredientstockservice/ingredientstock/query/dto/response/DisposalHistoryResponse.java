package com.ohgiraffers.ingredientstockservice.ingredientstock.query.dto.response;

import com.ohgiraffers.ingredientstockservice.ingredientstock.command.domain.aggregate.DisposalHistory;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@Builder
@RequiredArgsConstructor
public class DisposalHistoryResponse {

    private final List<DisposalHistory> disposalHistories;

}
