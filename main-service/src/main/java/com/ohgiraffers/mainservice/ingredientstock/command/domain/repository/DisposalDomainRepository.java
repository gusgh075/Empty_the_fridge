package com.ohgiraffers.mainservice.ingredientstock.command.domain.repository;

import com.ohgiraffers.mainservice.ingredientstock.command.domain.aggregate.DisposalHistory;

public interface DisposalDomainRepository {
    DisposalHistory save(DisposalHistory disposalHistory);
}
