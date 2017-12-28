DELETE FROM user_roles;
DELETE FROM users;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (username, email, password, first_name, last_name)
VALUES ('bigBoss', 'realchuck@gmail.com', '$2a$10$Sh0ZD2NFrzRRJJEKEWn8l.92ROEuzlVyzB9SV1AM8fdluPR0aC1ni', 'Chuck', 'Norris');

INSERT INTO users (username, email, password, first_name, last_name)
VALUES ('casualNerd', 'tommy@gmail.com', '$2a$10$WejOLxVuXRpOgr4IlzQJ.eT4UcukNqHlAiOVZj1P/nmc8WbpMkiju', 'Tommy', 'Nuomy');

INSERT INTO user_roles (role, user_id) VALUES
  ('USER',  100000),
  ('ADMIN', 100000),
  ('USER',  100001);