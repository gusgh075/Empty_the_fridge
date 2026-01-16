package com.ohgiraffers.recipeservice.config;

public class Constants {

    // 사용자 알림 활성화 여부 기본값
    public static final String NOTICE_DEFAULT_VALUE = "YYYYY";

    // ingredient stock notification threshold
    public static final Integer INGREDIENT_STOCK_EXPIRYHOUR_THRESHOLD = 72;
    public static final Integer INGREDIENT_STOCK_G_ML_THRESHOLD = 5;
    public static final Integer INGREDIENT_STOCK_EA_THRESHOLD = 50;

    // notification type
    public static final Integer NOTICE_TYPE_EXPIRED_SOON = 1;
    public static final Integer NOTICE_TYPE_LOW_STOCK = 2;
}
