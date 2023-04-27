DROP TABLE IF EXISTS courier CASCADE;
DROP TABLE IF EXISTS working_hours;
DROP TABLE IF EXISTS regions;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS delivery_hours;
DROP TABLE IF EXISTS status;

CREATE TABLE working_hours
(
    courier_id       INTEGER NOT NULL,
    working_hours TEXT    NOT NULL
--     working_hours VARCHAR    NOT NULL
);

CREATE TABLE delivery_hours
(
    order_id       INTEGER NOT NULL,
    delivery_hours TEXT    NOT NULL
);

CREATE TABLE regions
(
    courier_id       INTEGER NOT NULL,
    number_region INTEGER NOT NULL
);
CREATE TABLE courier
(
    courier_id      INTEGER PRIMARY KEY,
--     courier_type VARCHAR
    courier_type TEXT
);

CREATE TABLE orders
(
    order_id INTEGER PRIMARY KEY,
    cost    INTEGER NOT NULL,
    regions INTEGER NOT NULL,
    weight  INTEGER NOT NULL
);

create table status
(
    order_id       integer not null,
    courier_id     integer,
    created_at    timestamp(6) with time zone,
    date_complete timestamp(6),
    is_complete   boolean,
    primary key (order_id)
)