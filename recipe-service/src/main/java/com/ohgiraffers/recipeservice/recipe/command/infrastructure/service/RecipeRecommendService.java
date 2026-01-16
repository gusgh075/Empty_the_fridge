package com.ohgiraffers.recipeservice.recipe.command.infrastructure.service;

import java.util.Map;

import com.ohgiraffers.recipeservice.recipe.command.application.dto.request.RecipeRecommendRequest;
import com.ohgiraffers.recipeservice.recipe.command.application.dto.response.RecommendRecipeResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.ai.chat.client.ChatClient;


@Service
public class RecipeRecommendService {

	private final ChatClient chatClient;

	@Value("classpath:/prompts/recipe-recommend-user.st")
	private Resource userPromptResource;

	@Value("classpath:/prompts/recipe-recommend-system.st")
	private Resource systemPromptResource;

	public RecipeRecommendService(ChatClient.Builder builder) {
		// 기본 설정을 빌더를 통해 구성
		this.chatClient = builder.build();
	}

  public RecommendRecipeResponse getRecipeRecommendation(RecipeRecommendRequest request) {
    return chatClient.prompt()
        // 시스템 프롬프트 설정 (전문 요리사 페르소나 등)
        .system(s -> s.text(systemPromptResource))
        // 유저 프롬프트 설정 및 리소스 바인딩
        .user(u -> u.text(userPromptResource)
            .params(Map.of(
                "dishName", request.getDishName() != null ? request.getDishName() : "재료에 어울리는 요리",
                "ingredients", request.getIngredients(),
                "dislikedIngredients", request.getDislikedIngredients(),
                "skillLevel", request.getSkillLevel() != null ? request.getSkillLevel().name() : "BEGINNER",
                "tools", request.getTools(),
                "foodTypes", request.getFoodTypes(),
                "preference", request.getPreference(),
                "servings", request.getServings()
            ))
        )
        .call()
        .entity(RecommendRecipeResponse.class);
  }
}