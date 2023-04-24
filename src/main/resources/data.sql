INSERT INTO working_hours (courier, working_hours)
VALUES (2, '22:30'),
       (2, '08:00'),
       (3, '22:00'),
       (3, '22:00'),
       (4, '8:30');
INSERT INTO delivery_hours (order_id, delivery_hours)
VALUES (2, '22:30'),
       (2, '08:00'),
       (3, '22:00'),
       (3, '22:00'),
       (4, '8:30');

INSERT INTO regions (courier, number_region)
VALUES (2, 66),
       (2, 96),
       (3, 777),
       (4, 056);
INSERT INTO courier (courier, courier_type)
VALUES (2, 'FOOT'),
       (3, 'BIKE'),
       (4, 'AUTO');
INSERT INTO orders (orderID, cost, regions, weight)
VALUES (2, 2, 2, 22),
       (3, 3, 3, 33),
       (4, 4, 4, 44);