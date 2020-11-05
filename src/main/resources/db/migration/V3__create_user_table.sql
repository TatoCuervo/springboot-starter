-- User
DROP TABLE IF EXISTS users;
CREATE TABLE users
(
    id                      BIGINT  GENERATED ALWAYS AS IDENTITY,
    username                VARCHAR   NOT NULL,
    password                VARCHAR   NOT NULL,
    role                    VARCHAR   NOT NULL,
    PRIMARY KEY(id)
);