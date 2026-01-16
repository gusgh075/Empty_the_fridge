-- 테이블 삭제 (참조 역순)
DROP TABLE IF EXISTS `user`;

-- 1. 회원 테이블
CREATE TABLE `user`
(
    `user_no`               INT AUTO_INCREMENT PRIMARY KEY,
    `user_id`               VARCHAR(15)  NOT NULL UNIQUE CHECK (CHAR_LENGTH(`user_id`) BETWEEN 8 AND 15),
    `user_pwd`              VARCHAR(100) NOT NULL,
    `user_nickname`         VARCHAR(10)  NOT NULL UNIQUE,
    `user_email`            VARCHAR(50)  NOT NULL UNIQUE,
    `user_phonenum`         VARCHAR(11)  NOT NULL UNIQUE,
    `user_birthdate`        DATE         NOT NULL,
    `user_registered_at`    DATETIME     NOT NULL DEFAULT current_timestamp(),
    `user_is_notice_active` VARCHAR(20)  NOT NULL CHECK (`user_is_notice_active` REGEXP '^[YN]+$'),
    `created_at`            DATETIME     NOT NULL DEFAULT current_timestamp(),
    `updated_at`            DATETIME     NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
);

DROP TABLE IF EXISTS `dish_category`;
-- 2. 요리 카테고리 테이블
CREATE TABLE `dish_category`
(
    `dish_category_no`   INT AUTO_INCREMENT PRIMARY KEY,
    `dish_category_name` VARCHAR(100) NOT NULL UNIQUE,
    `created_at`         DATETIME     NOT NULL DEFAULT current_timestamp(),
    `updated_at`         DATETIME     NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
);

DROP TABLE IF EXISTS `notification_type`;
-- 3. 알림 타입 테이블
CREATE TABLE `notification_type`
(
    `notification_type_no`   INT AUTO_INCREMENT PRIMARY KEY,
    `notification_type_name` VARCHAR(20) NOT NULL UNIQUE
);

DROP TABLE IF EXISTS `ingredient`;
-- 4. 식자재 기초 정보 테이블
CREATE TABLE `ingredient`
(
    `ingredient_no`   INT AUTO_INCREMENT PRIMARY KEY,
    `ingredient_name` VARCHAR(30) NOT NULL UNIQUE,
    `created_at`      DATETIME    NOT NULL DEFAULT current_timestamp(),
    `updated_at`      DATETIME    NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
);

SET foreign_key_checks = 0;
DROP TABLE IF EXISTS `dish`;
-- 5. 요리(음식) 테이블
CREATE TABLE `dish`
(
    `dish_no`             INT AUTO_INCREMENT PRIMARY KEY,
    `user_no`             INT          NOT NULL,
    `dish_category_no`    INT          NOT NULL,
    `dish_name`           VARCHAR(100) NOT NULL,
    `dish_img_file_route` VARCHAR(300) NULL,
    `dish_is_marked`      BOOLEAN      NOT NULL DEFAULT FALSE,
    `created_at`          DATETIME     NOT NULL DEFAULT current_timestamp(),
    `updated_at`          DATETIME     NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
    FOREIGN KEY (`user_no`) REFERENCES `user` (`user_no`),
    FOREIGN KEY (`dish_category_no`) REFERENCES `dish_category` (`dish_category_no`)
);
SET foreign_key_checks = 1;

DROP TABLE IF EXISTS `recipe`;
-- 6. 레시피 테이블
CREATE TABLE `recipe`
(
    `recipe_no`         INT AUTO_INCREMENT PRIMARY KEY,
    `dish_no`           INT           NOT NULL,
    `recipe_ingredient` VARCHAR(1000) NOT NULL COMMENT '음식재료(JSON)',
    `recipe_cookery`    VARCHAR(2000) NOT NULL,
    `created_at`        DATETIME      NOT NULL DEFAULT current_timestamp(),
    `updated_at`        DATETIME      NOT NULL DEFAULT current_timestamp() on update current_timestamp(),
    FOREIGN KEY (`dish_no`) REFERENCES `dish` (`dish_no`)
);

DROP TABLE IF EXISTS `ingredient_stock`;
-- 7. 식자재 재고 테이블
CREATE TABLE `ingredient_stock`
(
    `ingredient_stock_no`             INT AUTO_INCREMENT PRIMARY KEY,
    `user_no`                         INT                                                   NOT NULL,
    `ingredient_stock_name`           VARCHAR(30)                                           NOT NULL,
    `ingredient_stock_expired_at`     DATE                                                  NOT NULL,
    `ingredient_stock_total_quantity` INT                                                   NOT NULL CHECK (`ingredient_stock_total_quantity` > 0),
    `ingredient_stock_total_cost`     INT                                                   NOT NULL CHECK (`ingredient_stock_total_cost` >= 0),
    `ingredient_stock_now_quantity`   INT                                                   NOT NULL,
    `ingredient_stock_category`       ENUM ('produce', 'livestock', 'seafood', 'processed') NOT NULL,
    `ingredient_stock_unit`           ENUM ('g', 'ml', 'ea')                                NOT NULL,
    `ingredient_stock_bought_at`      DATETIME                                              NOT NULL DEFAULT current_timestamp(),
    `created_at`                      DATETIME                                              NOT NULL DEFAULT current_timestamp(),
    `updated_at`                      DATETIME                                              NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
    FOREIGN KEY (`user_no`) REFERENCES `user` (`user_no`),
    CHECK (`ingredient_stock_now_quantity` <= `ingredient_stock_total_quantity`)
);

DROP TABLE IF EXISTS `disposal_histories`;
-- 8. 폐기 이력 테이블
CREATE TABLE `disposal_histories`
(
    `disposal_no`         INT AUTO_INCREMENT PRIMARY KEY,
    `ingredient_stock_no` INT          NOT NULL,
    `user_no`             INT          NOT NULL,
    `disposal_quantity`   INT          NOT NULL CHECK (`disposal_quantity` > 0),
    `disposal_cost`       INT          NOT NULL CHECK (`disposal_cost` >= 0),
    `disposal_reason`     VARCHAR(100) NULL CHECK (`disposal_reason` IS NULL OR
                                                   CHAR_LENGTH(`disposal_reason`) BETWEEN 1 AND 100),
    `disposal_at`         DATETIME     NOT NULL DEFAULT current_timestamp(),
    `created_at`          DATETIME     NOT NULL DEFAULT current_timestamp(),
    `updated_at`          DATETIME     NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
    FOREIGN KEY (`ingredient_stock_no`) REFERENCES `ingredient_stock` (`ingredient_stock_no`),
    FOREIGN KEY (`user_no`) REFERENCES `user` (`user_no`)
);

DROP TABLE IF EXISTS `notification`;
-- 9. 알림 테이블
CREATE TABLE `notification`
(
    `notification_no`         INT AUTO_INCREMENT PRIMARY KEY,
    `user_no`                 INT         NOT NULL,
    `notification_type_no`    INT         NOT NULL,
    `notification_content`    VARCHAR(50) NOT NULL,
    `notification_is_checked` BOOLEAN     NOT NULL DEFAULT TRUE,
    `created_at`              DATETIME    NOT NULL DEFAULT current_timestamp(),
    `updated_at`              DATETIME    NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
    FOREIGN KEY (`user_no`) REFERENCES `user` (`user_no`),
    FOREIGN KEY (`notification_type_no`) REFERENCES `notification_type` (`notification_type_no`)
);

DROP TABLE IF EXISTS `rcd_recipe`;
CREATE TABLE `rcd_recipe`
(
    `rcd_recipe_no`            INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    `user_no`                  INT                            NOT NULL,
    `dish_category_no`         INT                            NOT NULL,
    `rcd_recipe_dish_name`     VARCHAR(100)                   NOT NULL,
    `rcd_recipe_ingredients`   VARCHAR(1000)                  NOT NULL,
    `rcd_recipe_substitutions` VARCHAR(1000)                  NULL,
    `rcd_recipe_cookery`       VARCHAR(2000)                  NOT NULL,
    `rcd_recipe_tips`          VARCHAR(2000)                  NOT NULL,
    FOREIGN KEY (`user_no`) REFERENCES `user` (`user_no`),
    FOREIGN KEY (`dish_category_no`) REFERENCES `dish_category` (`dish_category_no`)
);

ALTER TABLE `dish_category`
    ADD CONSTRAINT UQ_dish_category_name UNIQUE (`dish_category_name`);
ALTER TABLE `notification_type`
    ADD CONSTRAINT `UQ_notification_type_name` UNIQUE (`notification_type_name`);

-- 사용자 아이디 길이 제한
ALTER TABLE `user`
    ADD CONSTRAINT CHK_user_id_length CHECK (CHAR_LENGTH(user_id) BETWEEN 8 AND 15);

-- 식자재 수량 및 비용 검증
ALTER TABLE `ingredient_stock`
    ADD CONSTRAINT CHK_stock_quantity_valid CHECK (ingredient_stock_now_quantity <= ingredient_stock_total_quantity),
    ADD CONSTRAINT CHK_stock_total_quantity_positive CHECK (ingredient_stock_total_quantity > 0),
    ADD CONSTRAINT CHK_stock_total_cost_min CHECK (ingredient_stock_total_cost >= 0);

-- 폐기 이력 검증
ALTER TABLE `disposal_histories`
    ADD CONSTRAINT CHK_disposal_quantity_positive CHECK (disposal_quantity > 0),
    ADD CONSTRAINT CHK_disposal_cost_min CHECK (disposal_cost >= 0),
    ADD CONSTRAINT CHK_disposal_reason_length CHECK (disposal_reason IS NULL OR CHAR_LENGTH(disposal_reason) BETWEEN 1 AND 100);

-- 알림 활성화 여부 검증
ALTER TABLE `user`
    ADD CONSTRAINT CHK_user_is_notice_active
        CHECK (user_is_notice_active REGEXP '^[YN]+$');