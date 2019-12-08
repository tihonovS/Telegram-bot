-- DROP TABLE IF EXISTS billionaires;
--
-- CREATE TABLE billionaires
-- (
--     id         INT AUTO_INCREMENT PRIMARY KEY,
--     first_name VARCHAR(250) NOT NULL,
--     last_name  VARCHAR(250) NOT NULL,
--     career     VARCHAR(250) DEFAULT NULL
-- );

INSERT INTO CITY (title)
VALUES ('Москва'),
       ('Минск');


INSERT INTO CITY_INFORMATION (description, city_id)
values ('Не забудьте посетить Красную Площадь. Ну а в ЦУМ можно и не заходить)))', 1),
       ('Не забудьте посететь смотровую площадку на Воробьёвых горах', 1),
       ('Не забудьте посететь Национальную библиотеку Беларуси', 2);