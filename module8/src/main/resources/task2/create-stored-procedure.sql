#CAR_MAKE
CREATE PROCEDURE `get_car_make_list`()
BEGIN
    SELECT * FROM car_make;
END;

CREATE PROCEDURE `get_car_make`(IN carMakeId INT)
BEGIN
    SELECT cm.* FROM car_make cm WHERE cm.car_make_id = carMakeId;
END;

CREATE PROCEDURE `create_car_make`(IN name VARCHAR(45), OUT newCarMakeID INT)
BEGIN
    INSERT INTO car_make (car_make) VALUES (name);
    SET newCarMakeID = (SELECT c.car_make_id FROM car_make c WHERE c.car_make = name);
END;

CREATE PROCEDURE `update_car_make`(IN carMakeId INT, name VARCHAR(45))
BEGIN
    UPDATE car_make SET car_make = name WHERE car_make_id = carMakeId;
END;

CREATE PROCEDURE `delete_car_make`(IN carMakeId INT)
BEGIN
    DELETE FROM car_make WHERE car_make_id = carMakeId;
END;

#CAR_MODEL
CREATE PROCEDURE `get_car_model_list`()
BEGIN
    SELECT model.*, cm.*
    FROM car_model model
             JOIN car_make cm ON model.car_make_id = cm.car_make_id;
END;

CREATE PROCEDURE `get_car_models_of_car_make_list`(IN carMakeId INT)
BEGIN
    SELECT model.*, cm.*
    FROM car_model model
             JOIN car_make cm ON model.car_make_id = cm.car_make_id
    WHERE model.car_make_id = carMakeId;
END;

CREATE PROCEDURE `get_car_model`(IN carModelId INT)
BEGIN
    SELECT model.*, cm.*
    FROM car_model model
             JOIN car_make cm ON model.car_make_id = cm.car_make_id
    WHERE model.car_model_id = carModelId;
END;

CREATE PROCEDURE `create_car_model`(IN name VARCHAR(45), carMakeId INT, OUT newCarModelID INT)
BEGIN
    INSERT INTO car_model (car_model, car_make_id) VALUES (name, carMakeId);
    SET newCarModelID = (SELECT c.car_model_id FROM car_model c WHERE c.car_model = name);
END;

CREATE PROCEDURE `update_car_model`(IN name VARCHAR(45), carMakeId INT, carModelId INT)
BEGIN
    UPDATE car_model
    SET car_model   = name,
        car_make_id = carMakeId
    WHERE car_model_id = carModelId;
END;

CREATE PROCEDURE `delete_car_model`(IN carModelId INT)
BEGIN
    DELETE FROM car_model WHERE car_model_id = carModelId;
END;

#CAR
CREATE PROCEDURE `get_car_list`()
BEGIN
    SELECT *
    FROM car
             JOIN car_model cm ON car.car_model_id = cm.car_model_id
             JOIN car_make m ON cm.car_make_id = m.car_make_id;
END;

CREATE PROCEDURE `get_car`(IN carId INT)
BEGIN
    SELECT *
    FROM car
             JOIN car_model cm ON car.car_model_id = cm.car_model_id
             JOIN car_make m ON cm.car_make_id = m.car_make_id
    WHERE car.car_id = carId;
END;

CREATE PROCEDURE `create_car`(IN year INT, mileage INT, fuelType VARCHAR(45), carModelId INT, OUT newCarID INT)
BEGIN
    INSERT INTO car (year, mileage, fuel_type, car_model_id) VALUES (year, mileage, fuelType, carModelId);
    SET newCarID = (SELECT c.car_id
                    FROM car c
                    WHERE c.year = year
                      AND c.mileage = mileage
                      AND c.fuel_type = fuelType
                      AND c.car_model_id = carModelId);
END;

CREATE PROCEDURE `update_car`(IN year INT, mileage INT, fuelType VARCHAR(45), carModelId INT, carId INT)
BEGIN
    UPDATE car
    SET year         = year,
        mileage      = mileage,
        fuel_type    = fuelType,
        car_model_id = carModelId
    WHERE car_id = carId;
END;

CREATE PROCEDURE `delete_car`(IN carId INT)
BEGIN
    DELETE FROM car WHERE car_id = carId;
END;