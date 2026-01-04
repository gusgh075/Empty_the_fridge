package com.ohgiraffers.hw22thteamproject.ingredientstock.repository;

import com.ohgiraffers.hw22thteamproject.ingredientstock.domain.IngredientStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface IngredientStockRepository extends JpaRepository<IngredientStock, Integer> {
    // 특정 사용자의 모든 식재료 목록 조회
    List<IngredientStock> findByUser_UserId(Integer userId);

    // 특정 사용자의 현재 보유 중인(owned) 식재료만 조회
    List<IngredientStock> findByUser_UserIdAndState(Integer userId, IngredientStock.State state);

    // 유통기한이 특정 날짜 이전인 데이터 조회 (알림 서비스용)
    List<IngredientStock> findByExpiredAtBeforeAndState(LocalDate date, IngredientStock.State state);
}