DROP TABLE IF EXISTS employee_model;
DROP TABLE IF EXISTS branch_model;

CREATE TABLE branch_model
(
    branch_id INT AUTO_INCREMENT UNIQUE PRIMARY KEY,
    address   VARCHAR(255) NOT NULL
);

CREATE TABLE employee_model
(
    employee_id INT AUTO_INCREMENT UNIQUE PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    surname     VARCHAR(255) NOT NULL,
    position    VARCHAR(255) NOT NULL
--     branch_id   INTEGER,
--     FOREIGN KEY (branch_id) REFERENCES branch_model (branch_id)
);