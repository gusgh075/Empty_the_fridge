package com.ohgiraffers.mainservice.ingredientstock.query.service;

import com.ohgiraffers.mainservice.ingredientstock.command.domain.aggregate.DisposalHistory;
import com.ohgiraffers.mainservice.ingredientstock.query.dto.response.DisposalHistoryResponse;
import com.ohgiraffers.mainservice.ingredientstock.query.mapper.DisposalMapper;
import com.ohgiraffers.mainservice.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DisposalQueryService {

    private final JwtTokenProvider jwtTokenProvider;
    private final DisposalMapper disposalMapper;

    public DisposalHistoryResponse getDisposalHistories(String refreshToken) {
        this.jwtTokenProvider.validateToken(refreshToken);
        long userNo = Long.parseLong(this.jwtTokenProvider.getUserNoFromJWT(refreshToken));

        // get all data from disposal_histories where user_no = userNo
        List<DisposalHistory> disposalHistories = this.disposalMapper.getAllDisposalHistoriesByUserNo(userNo);

        return DisposalHistoryResponse.builder()
                .disposalHistories(disposalHistories)
                .build();
    }

}
