package com.ohgiraffers.mainservice.ingredientstock.query.service;

import com.ohgiraffers.hw22thteamproject.ingredientstock.command.domain.aggregate.DisposalHistory;
import com.ohgiraffers.hw22thteamproject.ingredientstock.query.dto.response.DisposalHistoryResponse;
import com.ohgiraffers.hw22thteamproject.ingredientstock.query.mapper.DisposalMapper;
import com.ohgiraffers.hw22thteamproject.jwt.JwtTokenProvider;
import com.ohgiraffers.hw22thteamproject.user.command.domain.aggregate.User;
import com.ohgiraffers.hw22thteamproject.user.command.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DisposalQueryService {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final DisposalMapper disposalMapper;

    public DisposalHistoryResponse getDisposalHistories(String refreshToken) {
        this.jwtTokenProvider.validateToken(refreshToken);
        long userNo = Long.parseLong(this.jwtTokenProvider.getUserNoFromJWT(refreshToken));
        User user = this.userRepository.findByUserNo(userNo);

        // get all data from disposal_histories where user_no = userNo
        List<DisposalHistory> disposalHistories = this.disposalMapper.getAllDisposalHistoriesByUserNo(userNo);

        return DisposalHistoryResponse.builder()
                .disposalHistories(disposalHistories)
                .build();
    }

}
