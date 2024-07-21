

CREATE TABLE city (
    id SERIAL4 NOT NULL,
    name VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)

);

CREATE TABLE route (
    id SERIAL4 NOT NULL,
    departure_city_id INT4 NOT NULL,
    arrival_city_id INT4 NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (departure_city_id) REFERENCES city (id),
    FOREIGN KEY (arrival_city_id) REFERENCES city (id)
);


CREATE TABLE segments (
    id SERIAL4 NOT NULL,
    segment INT4 NOT NULL,
    segment_boundary INT4 NOT NULL,
    departure_city_id INT4 NOT NULL,
    arrival_city_id INT4 NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (departure_city_id) REFERENCES city (id),
    FOREIGN KEY (arrival_city_id) REFERENCES city (id)
);

CREATE TABLE route_segments (
    route_id INT4 NOT NULL,
    segments_id INT4 NOT NULL,
    PRIMARY KEY(route_id, segments_id),
    FOREIGN KEY (route_id) REFERENCES route(id),
    FOREIGN KEY (segments_id) REFERENCES segments(id)
);

CREATE TABLE traveller (
    id SERIAL4 NOT NULL,
    name VARCHAR(50) NOT NULL,
    traveller_amount INT4 NOT NULL,
    PRIMARY KEY (id)
);



CREATE TABLE ticket
(
    id           SERIAL4 NOT NULL,
    traveller_id INT4    NOT NULL,
    route_id     INT4    NOT NULL,
    price        INT4    NOT NULL,
    currency     CHAR(3) NOT NULL,
    result       TEXT    NOT NULL CHECK (result in ('success', 'failure')),
    change       INT4    DEFAULT NULL,
    lack_of      INT4    DEFAULT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (traveller_id) REFERENCES traveller (id),
    FOREIGN KEY (route_id) REFERENCES route (id),
    CHECK (
        (result = 'success' AND lack_of IS NULL AND change IS NOT NULL) OR
        (result = 'failure' AND change IS NULL AND lack_of IS NOT NULL)
        )
);

INSERT INTO city (name)
VALUES ('London'),
       ('Reading'),
       ('Swinden'),
       ('Bristol'),
       ('Birmingham'),
       ('Coventry'),
       ('Northampton');

INSERT INTO route (departure_city_id, arrival_city_id)
VALUES (1, 4),
       (4, 3),
       (5, 1);

INSERT INTO segments (segment, segment_boundary, departure_city_id, arrival_city_id)
VALUES (7, 4, 1, 4),
       (7, 5, 4, 3),
       (5, 2, 5, 1);

INSERT INTO route_segments (route_id, segments_id)
VALUES (1, 1),
       (2, 2),
       (3, 3);

INSERT INTO traveller (name, traveller_amount)
VALUES ('Sam', 20),
       ('Kai', 10),
       ('Katy', 27),
       ('Nelly', 5);
INSERT INTO ticket (traveller_id, route_id, price, currency, result, change, lack_of)
VALUES (3, 1, 25, 'GBP', 'success', 0, NULL),
       (1, 2, 25, 'GBP', 'failure', NULL, 5),
       (2, 3, 17, 'GBP', 'failure', NULL, 7);



