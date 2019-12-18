SET search_path TO project0, postgres;
DROP TABLE IF EXISTS users CASCADE;
DROP TABLE IF EXISTS account CASCADE;

CREATE TABLE IF NOT EXISTS users(
	username VARCHAR(25) PRIMARY KEY,
	password VARCHAR(25) NOT NULL,
	role varchar(20) NOT NULL,
	account_id INTEGER
	
);

CREATE TABLE IF NOT EXISTS account(
	account_id SERIAL PRIMARY KEY,
	balance numeric(20,2),
	approved bool
);

DELETE FROM project0.users WHERE username = 'Summer Smith'; 
DELETE FROM project0.users WHERE username = 'Morty Smith';
DELETE FROM project0.account WHERE account_id = 0; 
DELETE FROM project0.account WHERE account_id = 5; 
ALTER SEQUENCE project0.account_account_id_seq RESTART WITH 1 INCREMENT BY 1;
DELETE FROM project0.account WHERE account_id = 1;
INSERT INTO project0.account (balance, approved) VALUES (10.0, TRUE);
UPDATE project0.users SET account_id = 1 WHERE username = 'Summer Smith';
UPDATE project0.users SET account_id=NULL WHERE role = 'Admin';
ALTER TABLE users ADD FOREIGN KEY (account_id) REFERENCES account (account_id) ON DELETE CASCADE;
SELECT * FROM project0.users;
SELECT * FROM project0.users WHERE username = 'Jerry Smith ';
UPDATE project0.account SET approved