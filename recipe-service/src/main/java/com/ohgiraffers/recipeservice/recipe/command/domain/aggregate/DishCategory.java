package com.ohgiraffers.recipeservice.recipe.command.domain.aggregate;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "dish_category")
public class DishCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "dish_category_no", nullable = false)
	private Integer id;

	@Size(max = 100)
	@NotNull
	@Column(name = "dish_category_name", nullable = false, length = 100, unique = true) // unique 추가
	private String dishCategoryName;

	// mappedBy를 사용하여 Dish 엔티티의 필드명을 지정
	@Builder.Default
	@OneToMany(mappedBy = "dishCategoryNo")
	private Set<Dish> dishes = new LinkedHashSet<>();

	@NotNull
	@Builder.Default
	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt = LocalDateTime.now();

	@NotNull
	@Builder.Default
	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt = LocalDateTime.now();
}