CREATE TABLE crud.test_engineer_table (
  id SERIAL PRIMARY KEY,
  firstName TEXT NOT NULL,
  lastName TEXT NOT NULL,
  major VARCHAR(40),
  tel BIGINT);