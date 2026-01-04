package com.ohgiraffers.hw22thteamproject.disposal.repository;

import com.ohgiraffers.hw22thteamproject.disposal.domain.DisposalHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DisposalHistoryRepository extends JpaRepository<DisposalHistory, Integer> {
    // 특정 사용자의 폐기 이력 전체 조회
    List<DisposalHistory> findByUser_UserIdOrderByBaseTimeCreatedAtDesc(Integer userId);
    
    // 특정 식재료의 폐기 기록 조회
    List<DisposalHistory> findByIngredientStock_IngredientStockId(Integer ingredientStockId);
}