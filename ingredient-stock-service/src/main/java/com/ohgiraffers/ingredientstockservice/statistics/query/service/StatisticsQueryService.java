package com.ohgiraffers.ingredientstockservice.statistics.query.service;

import com.ohgiraffers.ingredientstockservice.jwt.JwtTokenProvider;
import com.ohgiraffers.ingredientstockservice.statistics.query.dto.request.DisposalCostRequest;
import com.ohgiraffers.ingredientstockservice.statistics.query.dto.request.MonthlyPurchaseRequest;
import com.ohgiraffers.ingredientstockservice.statistics.query.dto.response.*;
import com.ohgiraffers.ingredientstockservice.statistics.query.mapper.StatisticsMapper;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StatisticsQueryService {

	private final StatisticsMapper statisticsMapper;
	private final JwtTokenProvider jwtTokenProvider;

	/**
	 * 월별 지출
	 * @param userNo
	 * @param monthlyPurchaseRequest
	 * @return
	 */
	public Map<String, Object> getMonthlyPurchaseDetails(Long userNo,
		MonthlyPurchaseRequest monthlyPurchaseRequest) {

		List<MonthlyPurchaseDTO> list = statisticsMapper.selectMonthlyPurchaseList(userNo, monthlyPurchaseRequest);

		int totalCost = list.stream()
			.mapToInt(MonthlyPurchaseDTO::getCost)
			.sum();

		Map<String, Object> response = new HashMap<>();
		response.put("analysisList", list);  // 상세 내역
		response.put("totalCost", totalCost); // 총 합계

		return response;

	}

	/**
	 * 삭자재별 구매내역
	 * @param userNo
	 * @return
	 */
	public List<IngredientPurchaseDTO> getIngredientPurchase(Long userNo) {

		List<IngredientPurchaseDTO> list = statisticsMapper.selectIngredientPurchaseList(userNo);

		if (list.isEmpty()) {
			return list;
		}

		long total = list.stream()
			.mapToLong(IngredientPurchaseDTO::getTotalCost)
			.sum();

		for (IngredientPurchaseDTO dto : list) {
			if (total > 0) {
				double percentage = (double)dto.getTotalCost() / total * 100;
				dto.setPercentage(Math.round(percentage * 100) / 100.0);
			}
		}
		return list;
	}

	/**
	 * 카테고리별
	 * @param userNo
	 * @return
	 */
	public List<CategoryPurchaseDTO> getCategoryExpenseStats(Long userNo) {
		return statisticsMapper.selectCategoryPurchaseList(userNo);
	}

	/**
	 * 기간내폐기조회
	 * @param userNo
	 * @param disposalCostRequest
	 * @return
	 */
	public DisposalCostResponse getDisposalCost(Long userNo, DisposalCostRequest disposalCostRequest) {

		List<DisposalCostDTO> list = statisticsMapper.selectDisposalCostList(userNo, disposalCostRequest);

		long totalCost = 0;

		totalCost = list.stream().mapToLong(DisposalCostDTO::getDisposalCost)
			.sum();

		return DisposalCostResponse.builder()
			.totalDisposalCost(totalCost)
			.build();
	}

	/**
	 * 월별 폐기
	 * @param userNo
	 * @return
	 */
	public List<MonthlyDisposalDTO> getMonthlyDisposalList(Long userNo) {

		List<MonthlyDisposalDTO> list = statisticsMapper.selectMonthlyDisposalList(userNo);

		List<MonthlyDisposalDTO> resultList = new ArrayList<>();

		Map<String, MonthlyDisposalDTO> listMap = list.stream()
			.collect(Collectors.toMap(MonthlyDisposalDTO::getStatMonth, dto -> dto));

		LocalDate currentMonth = LocalDate.now();
		for (int i = 5; i >= 0; i--) {

			LocalDate lastMonth = currentMonth.minusMonths(i);
			String key = lastMonth.format(DateTimeFormatter.ofPattern("yyyy-MM"));

			if (listMap.containsKey(key)) {
				resultList.add(listMap.get(key));
			} else {
				MonthlyDisposalDTO emptyList = new MonthlyDisposalDTO();
				emptyList.setStatMonth(key);
				emptyList.setTotalCost(0L);
				emptyList.setDiscardCost(0L);
				emptyList.setWasteRate(0.0);
				resultList.add(emptyList);
			}
		}
		return resultList;
	}

}