-- Lesson
DROP TABLE IF EXISTS lesson;
CREATE TABLE lesson
(
    id                      BIGINT  GENERATED ALWAYS AS IDENTITY,
    name                    VARCHAR   NOT NULL,
    description             VARCHAR   NOT NULL,
    course_id               BIGINT NOT NULL,
    PRIMARY KEY(id),
    CONSTRAINT fk_course FOREIGN KEY (course_id) REFERENCES course(id)
);