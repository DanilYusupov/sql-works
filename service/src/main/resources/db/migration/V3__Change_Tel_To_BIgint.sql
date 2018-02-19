UPDATE crud.engineer SET tel = to_number(tel, '9999999');
UPDATE crud.engineer SET tel = '0' WHERE length(tel) < 7;
ALTER TABLE crud.engineer ALTER COLUMN tel TYPE BIGINT USING tel::BIGINT;