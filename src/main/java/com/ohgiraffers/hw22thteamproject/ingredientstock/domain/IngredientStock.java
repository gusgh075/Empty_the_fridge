package com.ohgiraffers.hw22thteamproject.ingredientstock.domain;

import com.ohgiraffers.hw22thteamproject.BaseTime;
import com.ohgiraffers.hw22thteamproject.user.domain.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "ingredient_stock")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class IngredientStock {
    @Id
    private Integer ingredientStockId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private String name;

    @Enumerated(EnumType.STRING)
    private Category category;

    private LocalDate expiredAt;
    private Integer totalQuantity;
    private Integer totalCost;
    private Integer nowQuantity;

    @Enumerated(EnumType.STRING)
    private Unit unit;

    @Enumerated(EnumType.STRING)
    private State state;

    private LocalDateTime boughtAt;

    @Embedded
    private BaseTime baseTime = new BaseTime();

    public enum Category { 채소, 과일, 육류, 해산물, 유제품, 냉동식품, 가공식품 }
    public enum Unit { g, kg, ea }
    public enum State { owned, disposal }
}