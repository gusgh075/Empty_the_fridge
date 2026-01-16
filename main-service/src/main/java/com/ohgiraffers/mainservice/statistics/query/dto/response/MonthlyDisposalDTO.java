package com.ohgiraffers.recipeservice.statistics.query.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MonthlyDisposalDTO {
  private String statMonth;
  private Long totalCost;
  private Long discardCost;
  private double wasteRate;
}
