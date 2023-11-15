DROP TABLE IF EXISTS car_rental;
DROP TABLE IF EXISTS employee_model;
DROP TABLE IF EXISTS reservation;
DROP TABLE IF EXISTS branch_model;

CREATE TABLE car_rental
(
    car_rental_id BIGINT AUTO_INCREMENT UNIQUE PRIMARY KEY,
    name          VARCHAR(255) NOT NULL,
    domain        VARCHAR(255) NOT NULL,
    address       VARCHAR(255) NOT NULL,
    owner         VARCHAR(255) NOT NULL,
    logo          VARCHAR(255) NOT NULL
);

CREATE TABLE branch_model
(
    branch_id     BIGINT AUTO_INCREMENT UNIQUE PRIMARY KEY,
    name          VARCHAR(255) NOT NULL,
    address       VARCHAR(255) NOT NULL,
    car_rental_id BIGINT,
    FOREIGN KEY (car_rental_id) REFERENCES car_rental (car_rental_id)

);

CREATE TABLE employee_model
(
    employee_id BIGINT AUTO_INCREMENT UNIQUE PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    surname     VARCHAR(255) NOT NULL,
    position    TINYINT      NOT NULL,
    branch_id   BIGINT,
    FOREIGN KEY (branch_id) REFERENCES branch_model (branch_id)
);

DROP TABLE IF EXISTS client_model;
CREATE TABLE client_model
(
    client_id BIGINT AUTO_INCREMENT UNIQUE PRIMARY KEY,
    name      VARCHAR(255) NOT NULL,
    surname   VARCHAR(255) NOT NULL,
    address   VARCHAR(255) NOT NULL,
    email     VARCHAR(255) NOT NULL
);

CREATE TABLE reservation
(
    reservation_id  BIGINT AUTO_INCREMENT UNIQUE PRIMARY KEY,
    customer        VARCHAR(255)  NOT NULL,
    car             VARCHAR(255)  NOT NULL,
    start_date      DATE          NOT NULL,
    end_date        DATE          NOT NULL,
    price           DECIMAL(7, 2) NOT NULL,
    start_branch_id BIGINT,
    end_branch_id   BIGINT,
    FOREIGN KEY (start_branch_id) REFERENCES branch_model (branch_id),
    FOREIGN KEY (end_branch_id) REFERENCES branch_model (branch_id)
);