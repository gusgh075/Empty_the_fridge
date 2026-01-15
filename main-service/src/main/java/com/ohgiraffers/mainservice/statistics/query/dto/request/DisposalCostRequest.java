package com.ohgiraffers.mainservice.statistics.query.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DisposalCostRequest {
  private String startDate;
  private String endDate;
}