package com.ohgiraffers.hw22thteamproject.disposal.domain;

import com.ohgiraffers.hw22thteamproject.BaseTime;
import com.ohgiraffers.hw22thteamproject.ingredientstock.domain.IngredientStock;
import com.ohgiraffers.hw22thteamproject.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "disposal_histories")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DisposalHistory {
    @Id
    private Integer disposalId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingredient_stock_id")
    private IngredientStock ingredientStock;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private Integer quantity;
    private Integer cost;
    private String reason;
    private LocalDateTime disposaledAt;

    @Embedded
    private BaseTime baseTime = new BaseTime();
}