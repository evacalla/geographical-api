DROP EXTENSION IF EXISTS postgis;

CREATE EXTENSION postgis;

DROP TABLE IF EXISTS nodes;

CREATE TABLE nodes(
    id SERIAL,
    latitude DOUBLE PRECISION NOT NULL,
    longitude DOUBLE PRECISION NOT NULL,
    address VARCHAR(100),
    schedule VARCHAR(50),
    capacity INT,
    type VARCHAR(50) NOT NULL,
    geo geometry(POINT, 4326),
    PRIMARY KEY (id)
);