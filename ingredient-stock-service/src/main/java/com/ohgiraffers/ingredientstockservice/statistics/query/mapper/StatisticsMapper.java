package com.ohgiraffers.ingredientstockservice.statistics.query.mapper;

import java.util.List;


import com.ohgiraffers.ingredientstockservice.statistics.query.dto.request.DisposalCostRequest;
import com.ohgiraffers.ingredientstockservice.statistics.query.dto.request.MonthlyPurchaseRequest;
import com.ohgiraffers.ingredientstockservice.statistics.query.dto.response.*;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StatisticsMapper {
	List<MonthlyPurchaseDTO> selectMonthlyPurchaseList(
		Long userNo,
		MonthlyPurchaseRequest monthlyPurchaseRequest
	);

	List<IngredientPurchaseDTO> selectIngredientPurchaseList(
		Long userNo
	);

	List<CategoryPurchaseDTO> selectCategoryPurchaseList(
		Long userNo
	);

	List<DisposalCostDTO> selectDisposalCostList(
		Long userNo,
		DisposalCostRequest disposalCostRequest
	);

	List<MonthlyDisposalDTO> selectMonthlyDisposalList(
		Long userNo
	);
}