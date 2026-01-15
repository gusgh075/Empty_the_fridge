package com.ohgiraffers.ingredientstockservice.ingredientstock.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "ingredient_stock")
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class IngredientStock {

    @Id
    @Column(name = "ingredient_stock_no", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ingredientStockNo;

    @Column(name = "user_no", nullable = false)
    private Long userNo;

    @Column(name = "ingredient_stock_name", nullable = false)
    private String ingredientStockName;

    @Column(name = "ingredient_stock_expired_at", nullable = false)
    private LocalDate ingredientStockExpiredAt;

    @Column(name = "ingredient_stock_total_quantity", nullable = false)
    private long ingredientStockTotalQuantity;

    @Column(name = "ingredient_stock_total_cost", nullable = false)
    private long ingredientStockTotalCost;

    @Column(name = "ingredient_stock_now_quantity", nullable = false)
    private long ingredientStockNowQuantity;

    @Column(name = "ingredient_stock_category", nullable = false)
    @Enumerated(EnumType.STRING)
    private StockCategory ingredientStockCategory;

    @Column(name = "ingredient_stock_unit", nullable = false)
    @Enumerated(EnumType.STRING)
    private StockUnit ingredientStockUnit;

    @Column(name = "ingredient_stock_bought_at", nullable = false)
    private LocalDate ingredientStockBoughtAt;

    @Column(name = "created_at", nullable = false)
    @CreatedDate // Entity 생성 시간을 자동 기록
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    @LastModifiedDate // Entity 값 변경 시간을 자동으로 기록하는 어노테이션
    private LocalDateTime updatedAt;


    public void setUserNo(Long userNo) {
        this.userNo = userNo;
    }

    public void InitTime() {
        this.ingredientStockNowQuantity = this.ingredientStockTotalQuantity;
    }

    public void updateIngredientStockNowQuantity(long ingredientStockNowQuantity) {
        this.ingredientStockNowQuantity = this.ingredientStockNowQuantity - ingredientStockNowQuantity;
    }
}
