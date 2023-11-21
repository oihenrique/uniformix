CREATE TABLE uniform (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    quantity INT NOT NULL,
    sex CHAR(1) NOT NULL,
    size VARCHAR(7) NOT NULL
);
