CREATE TABLE batch (
    id SERIAL PRIMARY KEY,
    batch_code VARCHAR(6) NOT NULL,
    description VARCHAR(255) NOT NULL,
    quantity INT NOT NULL,
    acquisition_date TIMESTAMP NOT NULL,
    id_supplier INTEGER REFERENCES supplier(id),
    id_category INTEGER REFERENCES category(id),
    uniform INTEGER ARRAY
);