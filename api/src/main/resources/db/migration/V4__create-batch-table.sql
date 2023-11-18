CREATE TABLE batch (
    id BIGINT PRIMARY KEY AUTO_INCREMENT NOT NULL,
    batch_Code VARCHAR(6) NOT NULL,
    description VARCHAR(255) NOT NULL,
    quantity INT NOT NULL,
    aquisition_Date TIMESTAMP NOT NULL
);
