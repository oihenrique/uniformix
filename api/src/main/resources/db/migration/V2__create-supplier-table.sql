CREATE TABLE supplier (
    id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    code VARCHAR(6) NOT NULL,
    name VARCHAR(64) NOT NULL,
    state TINYINT(1) NOT NULL
);