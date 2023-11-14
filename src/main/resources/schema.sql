DROP TABLE IF EXISTS employee_model;
DROP TABLE IF EXISTS branch_model;
DROP TABLE IF EXISTS car_rental;

CREATE TABLE car_rental
(
    car_rental_id      BIGINT AUTO_INCREMENT UNIQUE PRIMARY KEY,
    name    VARCHAR(255) NOT NULL,
    domain  VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    owner   VARCHAR(255) NOT NULL,
    logo    VARCHAR(255) NOT NULL
);

CREATE TABLE branch_model
(
    branch_id BIGINT AUTO_INCREMENT UNIQUE PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    address   VARCHAR(255) NOT NULL,
    car_rental_id BIGINT,
    FOREIGN KEY (car_rental_id) REFERENCES car_rental (car_rental_id)

);

CREATE TABLE employee_model
(
    employee_id BIGINT AUTO_INCREMENT UNIQUE PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    surname     VARCHAR(255) NOT NULL,
    position    TINYINT NOT NULL,
    branch_id   BIGINT,
    FOREIGN KEY (branch_id) REFERENCES branch_model (branch_id)
);


