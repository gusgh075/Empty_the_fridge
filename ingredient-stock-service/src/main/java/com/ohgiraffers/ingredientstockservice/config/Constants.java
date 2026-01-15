package com.ohgiraffers.ingredientstockservice.config;

public class Constants {

    // ingredient stock notification threshold
    public static final Integer INGREDIENT_STOCK_EXPIRYHOUR_THRESHOLD = 72;
    public static final Integer INGREDIENT_STOCK_G_ML_THRESHOLD = 5;
    public static final Integer INGREDIENT_STOCK_EA_THRESHOLD = 50;

    // notification type
    public static final Integer NOTICE_TYPE_EXPIRED_SOON = 1;
    public static final Integer NOTICE_TYPE_LOW_STOCK = 2;
}
