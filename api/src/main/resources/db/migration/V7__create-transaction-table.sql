CREATE EXTENSION IF NOT EXISTS pgcrypto;

CREATE TABLE transaction_history (
    id VARCHAR(36) DEFAULT uuid_generate_v4() PRIMARY KEY,
    protocol_number VARCHAR(16),
    id_uniform BIGINT,
    employee_name VARCHAR(150),
    id_unit BIGINT,
    quantity INT,
    operation_type VARCHAR(10),
    id_users VARCHAR(36),
    date TIMESTAMP,

    CONSTRAINT fk_uniform FOREIGN KEY (id_uniform) REFERENCES Uniform (id),
    CONSTRAINT fk_unit FOREIGN KEY (id_unit) REFERENCES Unit (id),
    CONSTRAINT fk_users FOREIGN KEY (id_users) REFERENCES Users (id)
);
