DROP TABLE IF EXISTS courier CASCADE;
DROP TABLE IF EXISTS working_hours;
DROP TABLE IF EXISTS regions;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS delivery_hours;

CREATE TABLE working_hours
(
    courier       INTEGER NOT NULL,
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
    courier       INTEGER NOT NULL,
    number_region INTEGER NOT NULL
);
CREATE TABLE courier
(
    courier      INTEGER PRIMARY KEY,
--     courier_type VARCHAR
    courier_type TEXT
);

CREATE TABLE orders
(
    orderID INTEGER PRIMARY KEY,
    cost    INTEGER NOT NULL,
    regions INTEGER NOT NULL,
    weight  INTEGER NOT NULL
);

create table status
(
    orderid       integer not null,
    courierid     integer,
    created_at    timestamp(6) with time zone,
    date_complete timestamp(6),
    is_complete   boolean,
    primary key (orderid)
)