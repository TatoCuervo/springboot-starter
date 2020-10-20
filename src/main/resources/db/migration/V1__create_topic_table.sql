DROP SEQUENCE IF EXISTS hibernate_sequence;
CREATE SEQUENCE hibernate_sequence;

-- Topic
DROP TABLE IF EXISTS topic;
CREATE TABLE topic
(
    id                      BIGINT  GENERATED ALWAYS AS IDENTITY,
    name                    VARCHAR  NOT NULL,
    description             VARCHAR  NOT NULL,
    PRIMARY KEY (id)
);

-- COURSE
DROP TABLE IF EXISTS course;
CREATE TABLE course
(
    id                      BIGINT  GENERATED ALWAYS AS IDENTITY,
    name                    VARCHAR   NOT NULL,
    description             VARCHAR   NOT NULL,
    topic_id                BIGINT NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_topic FOREIGN KEY (topic_id) REFERENCES topic(id)
);