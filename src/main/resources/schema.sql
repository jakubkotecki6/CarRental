USE car_rental;
CREATE TABLE car_rental (
    id INT AUTO_INCREMENT UNIQUE PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    internet_domain VARCHAR(255) NOT NULL,
    adress VARCHAR(255) NOT NULL,
    owner VARCHAR(255) NOT NULL
);