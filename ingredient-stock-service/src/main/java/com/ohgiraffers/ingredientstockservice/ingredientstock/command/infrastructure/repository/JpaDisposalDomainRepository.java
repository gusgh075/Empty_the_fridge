package com.ohgiraffers.ingredientstockservice.ingredientstock.command.infrastructure.repository;

import com.ohgiraffers.ingredientstockservice.ingredientstock.command.domain.aggregate.DisposalHistory;
import com.ohgiraffers.ingredientstockservice.ingredientstock.command.domain.repository.DisposalDomainRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaDisposalDomainRepository extends JpaRepository<DisposalHistory, Long>, DisposalDomainRepository {

}
