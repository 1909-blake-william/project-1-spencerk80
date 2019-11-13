update REIMBURSMENT
set status = (select id from REIMBURSEMENT_STATUS where status = 'PENDING'),
    resolved = null,
    resolver = NULL;

select * from reimbursment;