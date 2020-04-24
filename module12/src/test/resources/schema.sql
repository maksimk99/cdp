CREATE SCHEMA IF NOT EXISTS TRADE_SYSTEM;

DROP TABLE IF EXISTS `trade_system`.`order_has_goods`;
DROP TABLE IF EXISTS `trade_system`.`user_order`;
DROP TABLE IF EXISTS `trade_system`.`user_has_goods`;
DROP TABLE IF EXISTS `trade_system`.`goods`;
DROP TABLE IF EXISTS `trade_system`.`role_has_user`;
DROP TABLE IF EXISTS `trade_system`.`role`;
DROP TABLE IF EXISTS `trade_system`.`user`;
DROP TABLE IF EXISTS `trade_system`.`status`;

CREATE TABLE `trade_system`.`status`
(
    `status_id`   int(10) NOT NULL AUTO_INCREMENT,
    `status_name` varchar(45)      NOT NULL,
    PRIMARY KEY (`status_id`, `status_name`)
);

CREATE TABLE `trade_system`.`user`
(
    `user_id`  int(10)              NOT NULL AUTO_INCREMENT,
    `login`    varchar(45)  NOT NULL,
    `password` varchar(225) NOT NULL,
    PRIMARY KEY (`user_id`),
    UNIQUE KEY `login_UNIQUE` (`login`)
);

CREATE TABLE `trade_system`.`role`
(
    `role_id` int(10) NOT NULL AUTO_INCREMENT,
    `name`    varchar(45)      NOT NULL,
    PRIMARY KEY (`role_id`, `name`)
);

CREATE TABLE `trade_system`.`role_has_user`
(
    `role_id` int(10) NOT NULL,
    `user_id` int(10) NOT NULL,
    PRIMARY KEY (`role_id`, `user_id`),
    CONSTRAINT `fk_role_has_user_role1` FOREIGN KEY (`role_id`)
        REFERENCES `trade_system`.`role` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `fk_role_has_user_user1` FOREIGN KEY (`user_id`)
        REFERENCES `trade_system`.`user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `trade_system`.`goods`
(
    `id`          int(10) NOT NULL AUTO_INCREMENT,
    `name`        varchar(45)      NOT NULL,
    `description` varchar(1024)    NOT NULL,
    PRIMARY KEY (`id`)
);

CREATE TABLE `trade_system`.`user_has_goods`
(
    `goods_id` int(10) NOT NULL,
    `user_id`  int(10) NOT NULL,
    PRIMARY KEY (`goods_id`, `user_id`),
    CONSTRAINT `fk_basket_has_goods_goods1` FOREIGN KEY (`goods_id`)
        REFERENCES `trade_system`.`goods` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `fk_basket_has_goods_user1` FOREIGN KEY (`user_id`)
        REFERENCES `trade_system`.`user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `trade_system`.`user_order`
(
    `order_id`  int(10) NOT NULL AUTO_INCREMENT,
    `status_id` int(10) NOT NULL,
    `user_id`   int(10) NOT NULL,
    PRIMARY KEY (`order_id`, `status_id`, `user_id`),
    CONSTRAINT `fk_order_status1` FOREIGN KEY (`status_id`)
        REFERENCES `trade_system`.`status` (`status_id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `fk_order_user1` FOREIGN KEY (`user_id`)
        REFERENCES `trade_system`.`user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `trade_system`.`order_has_goods`
(
    `order_id` int(10) NOT NULL,
    `goods_id` int(10) NOT NULL,
    PRIMARY KEY (`order_id`, `goods_id`),
    CONSTRAINT `fk_order_has_goods_goods1` FOREIGN KEY (`goods_id`)
        REFERENCES `trade_system`.`goods` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `fk_order_has_goods_order1` FOREIGN KEY (`order_id`)
        REFERENCES `trade_system`.`user_order` (`order_id`) ON DELETE CASCADE ON UPDATE CASCADE
);
