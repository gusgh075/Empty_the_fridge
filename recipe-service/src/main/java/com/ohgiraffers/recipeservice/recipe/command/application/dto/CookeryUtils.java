package com.ohgiraffers.recipeservice.recipe.command.application.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 조리법에 관해 타입을 변환하는 클래스
 */
public class CookeryUtils {
	/**
	 * 조리법이 담긴 리스트에 넘버링하고 줄바꿈을 더해 문자열로 만듦
	 */
	public static String listToString(List<String> cookeryList) {
		if (cookeryList == null || cookeryList.isEmpty()) {
			return "";
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < cookeryList.size(); i++) {
			// 인덱스 i는 0부터 시작하므로 i + 1을 사용
			sb.append(i + 1).append(". ").append(cookeryList.get(i));

			// 마지막 줄이 아닐 때만 줄바꿈 문자 추가
			if (i < cookeryList.size() - 1) {
				sb.append("\n");
			}
		}
		return sb.toString();
	}

	/**
	 * 조리법이 담긴 문자열에서 숫자를 제거하고 List에 담음
	 */
	public static List<String> stringToList(String cookeryString) {
		List<String> list = new ArrayList<>();

		if (cookeryString == null || cookeryString.isBlank()) {
			return list;
		}

		// 1. 줄바꿈을 기준으로 한 줄씩 분리
		String[] lines = cookeryString.split("\n");

		for (String line : lines) {
			// 2. "1. " 형태에서 마침표(.)의 위치를 찾음
			int dotIndex = line.indexOf(". ");

			if (dotIndex != -1) {
				// 3. 마침표와 공백 뒷부분부터 끝까지 추출하여 리스트에 추가
				list.add(line.substring(dotIndex + 2).trim());
			} else {
				// 혹시 번호가 없는 줄이 있다면 그대로 추가
				list.add(line.trim());
			}
		}
		return list;
	}

	/**
	 * 조리법을 일정한 형식의 문자열로 바꾸는 함수
	 */
	public String convertIngredientFormat(String input) {
		// 1. 정규표현식 정의: 쉼표가 아닌 글자들 + (괄호 안의 내용)
		// ([^,(]+) : 쉼표나 여는 괄호가 아닌 글자들 (재료명)
		// \\(([^)]+)\\) : 괄호 안의 내용 (수량+단위)
		Pattern pattern = Pattern.compile("([^,(]+)\\(([^)]+)\\)");
		Matcher matcher = pattern.matcher(input);

		StringJoiner joiner = new StringJoiner(", ", "{", "}");

		while (matcher.find()) {
			String name = matcher.group(1).trim();      // 재료명 (예: 김치)
			String quantityUnit = matcher.group(2).trim(); // 수량단위 (예: 150.0g)

			// 원하는 형식으로 조립: "재료명": "수량단위"
			joiner.add(String.format("\"%s\": \"%s\"", name, quantityUnit));
		}

		return joiner.toString();
	}

}
