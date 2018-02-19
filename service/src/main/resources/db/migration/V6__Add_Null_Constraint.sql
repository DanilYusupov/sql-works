INSERT INTO crud.engineer (firstname, lastname, major, tel) VALUES ('Harry', 'Potter', null, '334215');
UPDATE crud.engineer SET major = '' WHERE major ISNULL;
ALTER TABLE crud.engineer ALTER COLUMN major SET NOT NULL;