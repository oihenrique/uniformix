CREATE TABLE uniform (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    quantity INT NOT NULL,
    sex VARCHAR(6) NOT NULL,
    size VARCHAR(7) NOT NULL,
	id_batch INTEGER REFERENCES batch(id)
);