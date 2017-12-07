DROP TABLE IF EXISTS dishes;
DROP TABLE IF EXISTS restaurants;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS votes;
DROP SEQUENCE IF EXISTS global_seq CASCADE;

CREATE SEQUENCE global_seq START 100000;

CREATE TABLE users
(
  id               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name             VARCHAR                  NOT NULL,
  email            VARCHAR                  NOT NULL,
  password         VARCHAR                  NOT NULL,
  registered       TIMESTAMP DEFAULT now()  NOT NULL,
  enabled          BOOL DEFAULT TRUE        NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx ON users (email);

CREATE TABLE user_roles
(
  user_id INTEGER NOT NULL,
  role    VARCHAR,
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE restaurants (
  id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  name       VARCHAR         NOT NULL,
  address       VARCHAR      NOT NULL,
  url       VARCHAR      NOT NULL
);

CREATE TABLE dishes (
  id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
  title       VARCHAR         NOT NULL,
  restaurant_id     INTEGER   NOT NULL,
  date_time   TIMESTAMP NOT NULL,
  price    INT       NOT NULL,
  category    VARCHAR,
  FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) ON DELETE CASCADE
);