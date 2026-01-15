package com.ohgiraffers.ingredientstockservice.ingredientstock.query.service;

import com.ohgiraffers.ingredientstockservice.ingredientstock.command.domain.aggregate.DisposalHistory;
import com.ohgiraffers.ingredientstockservice.ingredientstock.query.dto.response.DisposalHistoryResponse;
import com.ohgiraffers.ingredientstockservice.ingredientstock.query.mapper.DisposalMapper;
import com.ohgiraffers.ingredientstockservice.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DisposalQueryService {

    private final DisposalMapper disposalMapper;

    public DisposalHistoryResponse getDisposalHistories(String proviedUserNo) {
        long userNo = Long.parseLong(proviedUserNo);

        // get all data from disposal_histories where user_no = userNo
        List<DisposalHistory> disposalHistories = this.disposalMapper.getAllDisposalHistoriesByUserNo(userNo);

        return DisposalHistoryResponse.builder()
                .disposalHistories(disposalHistories)
                .build();
    }

}
