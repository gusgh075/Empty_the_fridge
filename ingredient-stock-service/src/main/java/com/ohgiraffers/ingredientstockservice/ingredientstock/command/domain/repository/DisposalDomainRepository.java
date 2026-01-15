package com.ohgiraffers.ingredientstockservice.ingredientstock.command.domain.repository;

import com.ohgiraffers.ingredientstockservice.ingredientstock.command.domain.aggregate.DisposalHistory;

public interface DisposalDomainRepository {
    DisposalHistory save(DisposalHistory disposalHistory);
}
