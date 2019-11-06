INSERT INTO reimbursement_status (id, status) VALUES (status_id_seq.NEXTVAL, 'PENDING');
INSERT INTO reimbursement_status (id, status) VALUES (status_id_seq.NEXTVAL, 'APPROVED');
INSERT INTO reimbursement_status (id, status) VALUES (status_id_seq.NEXTVAL, 'DENIED');

INSERT INTO reimbursement_type (id, type) VALUES (type_id_seq.NEXTVAL, 'FOOD');
INSERT INTO reimbursement_type (id, type) VALUES (type_id_seq.NEXTVAL, 'LODGING');
INSERT INTO reimbursement_type (id, type) VALUES (type_id_seq.NEXTVAL, 'CAR_RENTAL');
INSERT INTO reimbursement_type (id, type) VALUES (type_id_seq.NEXTVAL, 'FLIGHT');
INSERT INTO reimbursement_type (id, type) VALUES (type_id_seq.NEXTVAL, 'PUB_TRANS');
INSERT INTO reimbursement_type (id, type) VALUES (type_id_seq.NEXTVAL, 'MEDICAL');
INSERT INTO reimbursement_type (id, type) VALUES (type_id_seq.NEXTVAL, 'OTHER');

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