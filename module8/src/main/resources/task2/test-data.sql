INSERT INTO `car_make` (`car_make`)
VALUES ('Mercedes');
INSERT INTO `car_make` (`car_make`)
VALUES ('BMW');

INSERT INTO `car_model` (`car_model`, `car_make_id`)
VALUES ('C63_AMG', '1');
INSERT INTO `car_model` (`car_model`, `car_make_id`)
VALUES ('M5', '2');
INSERT INTO `car_model` (`car_model`, `car_make_id`)
VALUES ('M8', '2');

INSERT INTO `car` (`year`, `mileage`, `fuel_type`, `car_model_id`)
VALUES ('2017', '90300', 'Bensin', '1');
INSERT INTO `car` (`year`, `mileage`, `fuel_type`, `car_model_id`)
VALUES ('2016', '110300', 'Diesel', '2');
INSERT INTO `car` (`year`, `mileage`, `fuel_type`, `car_model_id`)
VALUES ('2015', '130300', 'Gas', '1');
INSERT INTO `car` (`year`, `mileage`, `fuel_type`, `car_model_id`)
VALUES ('2017', '70300', 'Bensin', '3');
INSERT INTO `car` (`year`, `mileage`, `fuel_type`, `car_model_id`)
VALUES ('2017', '70300', 'Diesel', '1');
