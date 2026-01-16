package com.ohgiraffers.recipeservice.recipe.command.domain.aggregate;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "rcd_recipe")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecommendRecipe {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "rcd_recipe_no", nullable = false)
  private Integer id;

  @NotNull
  @Column(name = "user_no", nullable = false)
  private Integer userNo;

  @NotNull
  @Column(name = "dish_category_no", nullable = false)
  private Integer dishCategoryNo;

  @Size(max = 100)
  @NotNull
  @Column(name = "rcd_recipe_dish_name", nullable = false, length = 20)
  private String rcdRecipeDishName;

  @Size(max = 1000)
  @NotNull
  @Column(name = "rcd_recipe_ingredients", nullable = false, length = 1000)
  private String rcdRecipeIngredients;

  @Size(max = 1000)
  @Column(name = "rcd_recipe_substitutions", length = 1000)
  private String rcdRecipeSubstitutions;

  @Size(max = 2000)
  @NotNull
  @Column(name = "rcd_recipe_cookery", nullable = false, length = 2000)
  private String rcdRecipeCookery;

  @Size(max = 2000)
  @NotNull
  @Column(name = "rcd_recipe_tips", nullable = false, length = 2000)
  private String rcdRecipeTips;
}