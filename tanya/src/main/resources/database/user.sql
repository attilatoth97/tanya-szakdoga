INSERT INTO person(first_name, last_name, date_of_birth, place_of_birth, phone_number, email) VALUES('Admin','Admin', '2000-01-01', 'place', null, 'admin@test');
INSERT INTO person(first_name, last_name, date_of_birth, place_of_birth, phone_number, email) VALUES('User','User', '2000-01-01', 'place', null, 'user@test');
INSERT INTO user_table(user_name, password, person_id) VALUES('admin','admin', 1);
INSERT INTO user_table(user_name, password, person_id) VALUES('user','user', 2);
INSERT INTO user_role(user_id, user_role) VALUES(1, 'ADMIN');
INSERT INTO user_role(user_id, user_role) VALUES(2, 'USER');