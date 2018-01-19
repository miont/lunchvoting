DELETE FROM user_roles;
DELETE FROM users;
-- ALTER SEQUENCE global_seq RESTART WITH 100000;

-- Users
INSERT INTO users (username, email, password, first_name, last_name)
VALUES ('bigboss', 'realchuck@gmail.com', '$2a$10$FuaITg3NDcVwL4/t7cg6WOF/vNsLPLfnf6DHwNJQoCFOQwvAcCHai', 'Chuck', 'Norris');

INSERT INTO users (username, email, password, first_name, last_name)
VALUES ('casualnerd', 'tommy@gmail.com', '$2a$10$H/Z8SdyvI.jDqNP9Gtd0LOGAkgQ5eYjf8JfSwKAbPQrprVLtf.41O', 'Tom', 'Smith');

INSERT INTO users (username, email, password, first_name, last_name)
VALUES ('justuser', 'homer@gmail.com', '$2a$10$n.AH55lFQpscX/ZPUKjlS.QzCTkPEnK9BgnoSz2sXajnym.hzpWKK', 'Homer', 'Simpson');

INSERT INTO user_roles (role, user_id) VALUES
  ('USER',  1),
  ('ADMIN', 1),
  ('USER',  2),
  ('USER',  3);

-- Restaurants
INSERT INTO restaurants (name, address, url)
VALUES ('Negril Village', '70 West 3rd St, New York, NY 10012, USA', 'http://negrilvillageatl.com');

INSERT INTO restaurants (name, address, url)
VALUES ('J B Alberto''s Pizza', '1326 W Morse Ave Chicago, IL, 60626', '');

-- Dishes
-- today() probably works only in hsqldb
INSERT INTO dishes (name, price, restaurant_id, date)
VALUES ('Guava BBQ Wings', 1300, 1, CURRENT_DATE - INTERVAL '1' DAY);

INSERT INTO dishes (name, price, restaurant_id, date)
VALUES ('Curry Goat Stew Roti', 2200, 1, CURRENT_DATE - INTERVAL '1' DAY);

INSERT INTO dishes (name, price, restaurant_id, date)
VALUES ('Quinoa Ital Stew', 1700, 1, CURRENT_DATE - INTERVAL '1' DAY);

INSERT INTO dishes (name, price, restaurant_id, date)
VALUES ('Mango Blue Cheese Salad', 1400, 1, CURRENT_DATE);

INSERT INTO dishes (name, price, restaurant_id, date)
VALUES ('Jerk Chicken Wings', 1300, 1, CURRENT_DATE);

INSERT INTO dishes (name, price, restaurant_id, date)
VALUES ('Curry Shrimp', 2700, 1, CURRENT_DATE);

INSERT INTO dishes (name, price, restaurant_id, date)
VALUES ('Fettuccine Alfredo', 1095, 2, CURRENT_DATE);

INSERT INTO dishes (name, price, restaurant_id, date)
VALUES ('Broiled Half Chicken Dinner', 950, 2, CURRENT_DATE);

INSERT INTO dishes (name, price, restaurant_id, date)
VALUES ('San Pellegrino', 195, 2, CURRENT_DATE);

INSERT INTO votes (user_id, restaurant_id, date, time)
VALUES (1, 1, CURRENT_DATE, '08:00:00');

INSERT INTO votes (user_id, restaurant_id, date, time)
VALUES (2, 1, CURRENT_DATE, '10:00:00');

INSERT INTO votes (user_id, restaurant_id, date, time)
VALUES (1, 2, CURRENT_DATE - INTERVAL '1' DAY, '09:00:00');