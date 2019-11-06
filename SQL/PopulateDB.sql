INSERT INTO reimbursement_status (id, status) VALUES (status_id_seq.NEXTVAL, 'Pending');
INSERT INTO reimbursement_status (id, status) VALUES (status_id_seq.NEXTVAL, 'Approved');
INSERT INTO reimbursement_status (id, status) VALUES (status_id_seq.NEXTVAL, 'Denied');

INSERT INTO reimbursement_type (id, type) VALUES (type_id_seq.NEXTVAL, 'Food');
INSERT INTO reimbursement_type (id, type) VALUES (type_id_seq.NEXTVAL, 'Lodging');
INSERT INTO reimbursement_type (id, type) VALUES (type_id_seq.NEXTVAL, 'Car Rental');
INSERT INTO reimbursement_type (id, type) VALUES (type_id_seq.NEXTVAL, 'Flight');
INSERT INTO reimbursement_type (id, type) VALUES (type_id_seq.NEXTVAL, 'Pub Trans');
INSERT INTO reimbursement_type (id, type) VALUES (type_id_seq.NEXTVAL, 'Medical');
INSERT INTO reimbursement_type (id, type) VALUES (type_id_seq.NEXTVAL, 'Other');

INSERT INTO user_role (id, role) VALUES (role_id_seq.NEXTVAL, 'Manager');
INSERT INTO user_role (id, role) VALUES (role_id_seq.NEXTVAL, 'Employee');

INSERT INTO users (id, username, password, firstname, lastname, email, role)
VALUES (user_id_seq.NEXTVAL, 'spencerk', 'Sushi888', 'Kristoffer', 'Spencer', 'kristoffer.spencer@revature.net',
(SELECT id FROM user_role WHERE role = 'Manager'));
INSERT INTO users (id, username, password, firstname, lastname, email, role)
VALUES (user_id_seq.NEXTVAL, 'espresso', 'Cheese888', 'Jacob', 'Voli', 'voli1376@gmail.com',
(SELECT id FROM user_role WHERE role = 'Employee'));
INSERT INTO users (id, username, password, firstname, lastname, email, role)
VALUES (user_id_seq.NEXTVAL, 'dobie', 'Cheese888', 'Patrick', 'Johnson', 'cloud5534@gmail.com',
(SELECT id FROM user_role WHERE role = 'Employee'));
INSERT INTO users (id, username, password, firstname, lastname, email, role)
VALUES (user_id_seq.NEXTVAL, 'chappellf', 'Cheese888', 'Franklin', 'Chappell', 'Frank.Chappell204@gmail.com',
(SELECT id FROM user_role WHERE role = 'Employee'));