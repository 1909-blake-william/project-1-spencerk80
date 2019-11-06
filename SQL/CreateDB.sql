CREATE TABLE reimbursement_status (

    id          INT             PRIMARY KEY,
    status      VARCHAR2(10)    NOT NULL UNIQUE

);

CREATE TABLE reimbursement_type (

    id          INT             PRIMARY KEY,
    type        VARCHAR2(10)    NOT NULL UNIQUE

);

CREATE TABLE user_role (

    id          INT             PRIMARY KEY,
    role        VARCHAR2(10)    NOT NULL UNIQUE

);

CREATE TABLE users (

    id          INT             PRIMARY KEY,
    username    VARCHAR2(50)    NOT NULL UNIQUE,
    password    VARCHAR2(50)    NOT NULL,
    firstname   VARCHAR2(100)   NOT NULL,
    lastname    VARCHAR2(100)   NOT NULL,
    email       VARCHAR2(150)   NOT NULL UNIQUE,
    role        INT             REFERENCES user_role(id)

);

CREATE TABLE reimbursment (

    id          INT             PRIMARY KEY,
    amount      NUMBER(8,2)     NOT NULL,
    submitted   TIMESTAMP       NOT NULL,
    resolved    TIMESTAMP,
    description VARCHAR2(250),
    receipt     BLOB,
    author      INT             REFERENCES users(id),
    resolver    INT             REFERENCES users(id),
    status      INT             REFERENCES reimbursement_status(id),
    type        INT             REFERENCES reimbursement_type(id)

);

CREATE SEQUENCE status_id_seq;
CREATE SEQUENCE type_id_seq;
CREATE SEQUENCE role_id_seq;
CREATE SEQUENCE user_id_seq;
CREATE SEQUENCE rmbsmnt_id_seq;