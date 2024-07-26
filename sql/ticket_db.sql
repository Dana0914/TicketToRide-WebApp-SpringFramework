

CREATE TABLE city (
    id SERIAL4 NOT NULL,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)

);



CREATE TABLE traveller (
    id SERIAL4 NOT NULL,
    name VARCHAR(50) NOT NULL,
    traveller_amount INT4 NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE route (
    id SERIAL4 NOT NULL,
    departure_city_id INT4 NOT NULL,
    arrival_city_id INT4 NOT NULL,
    segment INT4 NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (departure_city_id) REFERENCES city (id),
    FOREIGN KEY (arrival_city_id) REFERENCES city (id)
);

CREATE TABLE ticket
(
    id           SERIAL4 NOT NULL,
    traveller_id INT4    NOT NULL,
    price        INT4    NOT NULL,
    currency     CHAR(3) NOT NULL,
    segments INT4 NOT NULL,
    departure VARCHAR(20) NOT NULL,
    arrival VARCHAR(20) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (traveller_id) REFERENCES traveller (id)

);

INSERT INTO city (name)
VALUES ('London'),
       ('Reading'),
       ('Swinden'),
       ('Bristol'),
       ('Birmingham'),
       ('Coventry'),
       ('Northampton');


INSERT INTO traveller (name, traveller_amount)
VALUES ('Sam', 20),
       ('Kai', 10),
       ('Katy', 27);

INSERT INTO ticket (traveller_id, price, currency, segments, departure, arrival)
VALUES (3,  25, 'GBP', 7, 'London', 'Bristol'),
       (1,  25, 'GBP', 5,  'Bristol', 'Swinden'),
       (2,  17, 'GBP', 2,  'Birmingham', 'London');

INSERT INTO route (departure_city_id, arrival_city_id, segment)
VALUES
       (5, 6, 1),
       (5, 4, 3),
       (5, 3, 4),
       (5, 7, 3),
       (5, 1, 5),
       (5, 2, 6),
       (6, 5, 1),
       (6, 7, 2),
       (6, 1, 4),
       (6, 4, 4),
       (6, 2, 5),
       (6, 3, 5),
       (7, 1, 2),
       (7, 2, 3),
       (7, 3, 7),
       (7, 4, 6),
       (7, 5, 3),
       (7, 6, 2),
       (1, 2, 1),
       (1, 3, 5),
       (1, 4, 7),
       (1, 7, 2),
       (1, 6, 4),
       (1, 5, 5),
       (2, 1, 1),
       (2, 3, 4),
       (2, 4, 6),
       (2, 7, 3),
       (2, 6, 5),
       (2, 5, 6),
       (3, 4, 2),
       (3, 2, 4),
       (3, 1, 5),
       (3, 5, 4),
       (3, 6, 5),
       (3, 7, 7),
       (4, 3, 2),
       (4, 5, 3),
       (4, 6, 4),
       (4, 2, 6),
       (4, 1, 7);


