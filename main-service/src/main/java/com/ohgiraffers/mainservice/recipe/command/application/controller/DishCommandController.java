package com.ohgiraffers.recipeservice.recipe.command.application.controller;


import com.ohgiraffers.recipeservice.common.dto.ApiResponse;
import com.ohgiraffers.recipeservice.recipe.command.application.dto.request.DishCreateRequest;
import com.ohgiraffers.recipeservice.recipe.command.application.dto.request.DishUpdateRequest;
import com.ohgiraffers.recipeservice.recipe.command.application.service.DishCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/dishes")
@RequiredArgsConstructor
@Tag(name = "Dish Command API", description = "요리(Dish) 등록, 수정, 삭제 관련 API")
public class DishCommandController {

    private final DishCommandService dishCommandService;

    @Operation(summary = "요리 등록", description = "새로운 요리를 등록합니다.")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "요리 등록 성공")
    @PostMapping
    public ResponseEntity<ApiResponse<Void>> registDish(@RequestBody DishCreateRequest request,
                                                        @AuthenticationPrincipal UserDetails userDetails) {

        String username = userDetails.getUsername();
        dishCommandService.registDish(request, username);

        return ResponseEntity.ok(ApiResponse.success(null));
    }

    @Operation(summary = "요리 수정", description = "기존 요리의 정보를 수정합니다.")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "요리 수정 성공")
    @PutMapping("/update")
    public ResponseEntity<ApiResponse<Void>> updateDish(
            @RequestBody DishUpdateRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {

        String username = userDetails.getUsername();
        dishCommandService.updateDish(request, username);

        return ResponseEntity.ok(ApiResponse.success(null));
    }

    @Operation(summary = "요리 삭제", description = "요리를 삭제하고 연관된 레시피도 삭제합니다.")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "요리 삭제 성공")
    @DeleteMapping("/{dishNo}")
    public ResponseEntity<ApiResponse<Void>> deleteDish(
            @Parameter(description = "요리 번호") @PathVariable Integer dishNo,
            @AuthenticationPrincipal UserDetails userDetails) {

        String username = userDetails.getUsername();
        dishCommandService.deleteDish(dishNo, username);

        return ResponseEntity.ok(ApiResponse.success(null));
    }
}
