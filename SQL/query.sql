INSERT INTO reimbursment
VALUES (rmbsmnt_id_seq.NEXTVAL, 100, SYSDATE, NULL, 'Why I want money', NULL, 
(SELECT id FROM users WHERE firstname = 'Franklin' AND lastname = 'Chappell'),
NULL, (SELECT id FROM reimbursement_status WHERE status = 'PENDING'),
(SELECT id FROM reimbursement_type WHERE type = 'FOOD'))