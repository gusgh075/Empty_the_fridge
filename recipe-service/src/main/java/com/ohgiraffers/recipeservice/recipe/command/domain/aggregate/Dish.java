package com.ohgiraffers.recipeservice.recipe.command.domain.aggregate;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "dish")
public class Dish {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "dish_no", nullable = false)
	private Integer dishNo;

	@NotNull
	@Column(name = "user_no", nullable = false)
	private Long userNo;

	@Size(max = 100)
	@NotNull
	@Column(name = "dish_name", nullable = false, length = 20, unique = true) // unique 추가
	private String dishName;

	@Size(max = 300)
	@Null
	@Column(name = "dish_img_file_route", length = 300)
	private String dishImgFileRoute;

	@NotNull
	@Builder.Default // Builder 사용 시 기본값 유지를 위해 필요
	@Column(name = "dish_is_marked", nullable = false)
	private Boolean dishIsMarked = false;

	@Builder.Default
	@OneToMany(mappedBy = "dish") // Recipe 엔티티 내의 필드명과 일치해야 함
	private Set<Recipe> recipes = new LinkedHashSet<>();

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "dish_category_no", nullable = false)
	private DishCategory dishCategoryNo;

	@NotNull
	@Builder.Default
	@ColumnDefault("current_timestamp()")
	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime createdAt = LocalDateTime.now();

	@NotNull
	@Builder.Default
	@Column(name = "updated_at", nullable = false)
	private LocalDateTime updatedAt = LocalDateTime.now();
}