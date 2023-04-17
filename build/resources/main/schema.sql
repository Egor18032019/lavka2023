DROP TABLE IF EXISTS courier CASCADE;
DROP TABLE IF EXISTS working_hours;
DROP TABLE IF EXISTS regions;

CREATE TABLE working_hours
(
    courier       INTEGER NOT NULL,
    working_hours TEXT    NOT NULL
--     working_hours VARCHAR    NOT NULL
);
CREATE TABLE regions
(
    courier       INTEGER NOT NULL,
    number_region INTEGER NOT NULL
);
CREATE TABLE courier
(
    courier   INTEGER PRIMARY KEY,
--     courier_type VARCHAR
    courier_type TEXT
);