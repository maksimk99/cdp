DROP TABLE IF EXISTS `car_has_car_feature`;
DROP TABLE IF EXISTS `car_feature`;
DROP TABLE IF EXISTS `car`;
DROP TABLE IF EXISTS `car_model`;
DROP TABLE IF EXISTS `car_make`;

CREATE TABLE `car_make`
(
    `car_make_id` int(11)     NOT NULL AUTO_INCREMENT,
    `car_make`    varchar(45) NOT NULL UNIQUE,
    PRIMARY KEY (`car_make_id`)
);

CREATE TABLE `car_model`
(
    `car_model_id` int(11)     NOT NULL AUTO_INCREMENT,
    `car_model`    varchar(45) NOT NULL UNIQUE,
    `car_make_id`  int(11)     NOT NULL,
    PRIMARY KEY (`car_model_id`),
    CONSTRAINT `fk_car_make` FOREIGN KEY (`car_make_id`) REFERENCES `car_make` (`car_make_id`)
        ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE `car`
(
    `car_id`       int(11)     NOT NULL AUTO_INCREMENT,
    `year`         int(11)     NOT NULL,
    `mileage`      int(11) DEFAULT NULL,
    `fuel_type`    varchar(45) NOT NULL,
    `car_model_id` int(11)     NOT NULL,
    PRIMARY KEY (`car_id`),
    CONSTRAINT `car_model` FOREIGN KEY (`car_model_id`) REFERENCES `car_model` (`car_model_id`)
        ON DELETE NO ACTION ON UPDATE NO ACTION
);

CREATE TABLE `car_feature`
(
    `car_feature_id` int(11)     NOT NULL AUTO_INCREMENT,
    `car_feature`    varchar(45) NOT NULL,
    PRIMARY KEY (`car_feature_id`)
);

CREATE TABLE `car_has_car_feature`
(
    `car_id`         INT(11) NOT NULL,
    `car_feature_id` INT(11) NOT NULL,
    PRIMARY KEY (`car_id`, `car_feature_id`),
    CONSTRAINT `fk_car_has_car_feature_car1` FOREIGN KEY (`car_id`) REFERENCES `car` (`car_id`)
        ON DELETE CASCADE ON UPDATE NO ACTION,
    CONSTRAINT `fk_car_has_car_feature_car_feature1` FOREIGN KEY (`car_feature_id`)
        REFERENCES `car_feature` (`car_feature_id`) ON DELETE CASCADE ON UPDATE NO ACTION
);