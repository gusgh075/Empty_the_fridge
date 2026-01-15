package com.ohgiraffers.ingredientstockservice.ingredientstock.query.mapper;

import com.ohgiraffers.ingredientstockservice.ingredientstock.command.domain.aggregate.DisposalHistory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DisposalMapper {

    List<DisposalHistory> getAllDisposalHistoriesByUserNo(long userNo);
}
