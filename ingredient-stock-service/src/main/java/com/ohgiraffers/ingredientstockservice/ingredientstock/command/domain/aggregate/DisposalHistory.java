package com.ohgiraffers.ingredientstockservice.ingredientstock.command.domain.aggregate;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "disposal_histories", schema = "backend_team3_db")
public class DisposalHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "disposal_no", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "ingredient_stock_no", nullable = false)
    private long ingredientStockNo;

    @NotNull
    @Column(name = "user_no", nullable = false)
    private Long userNo;

    @NotNull
    @Column(name = "disposal_quantity", nullable = false)
    private Long disposalQuantity;

    @NotNull
    @Column(name = "disposal_cost", nullable = false)
    private Double disposalCost;

    @Size(max = 100)
    @Column(name = "disposal_reason", length = 100)
    private String disposalReason;

    @NotNull
    @ColumnDefault("current_timestamp()")
    @Column(name = "disposal_at", nullable = false)
    private LocalDateTime disposalAt;

    @NotNull
    @Column(name = "created_at", nullable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @NotNull
    @Column(name = "updated_at", nullable = false)
    @CreatedDate
    private LocalDateTime updatedAt;

    public static DisposalHistory createDisposalHistory(
            long ingredientStockNo,
            long userNo,
            long disposalQuantity,
            double disposalCost,
            String disposalReason
    ) {
        DisposalHistory disposalHistory = new DisposalHistory();
        disposalHistory.ingredientStockNo = ingredientStockNo;
        disposalHistory.userNo = userNo;
        disposalHistory.disposalQuantity = disposalQuantity;
        disposalHistory.disposalCost = disposalCost;
        disposalHistory.disposalReason = disposalReason;
        disposalHistory.disposalAt = LocalDateTime.now();
        disposalHistory.createdAt = LocalDateTime.now();
        disposalHistory.updatedAt = LocalDateTime.now();
        return disposalHistory;
    }


}