DELETE FROM user_roles;
DELETE FROM users;
-- ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (username, email, password, first_name, last_name)
VALUES ('bigBoss', 'realchuck@gmail.com', '123', 'Chuck', 'Norris');

INSERT INTO users (username, email, password, first_name, last_name)
VALUES ('casualNerd', 'tommy@gmail.com', '321', 'Tom', 'Smith');

INSERT INTO user_roles (role, user_id) VALUES
  ('USER',  1),
  ('ADMIN', 1),
  ('USER',  2);