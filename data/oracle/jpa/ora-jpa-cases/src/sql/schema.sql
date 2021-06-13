create table DEPT
(
    ID NUMBER(10) not null
        primary key,
    NAME VARCHAR2(255 char)
);


create table EMP
(
    ID NUMBER(6) not null
        primary key,
    NAME VARCHAR2(55) not null,
    DEPARTMENT_ID NUMBER(4)
);

ALTER TABLE EMP ADD CONSTRAINT EMP_CC_DEPT_FK FOREIGN KEY(DEPARTMENT_ID)  REFERENCES DEPT(ID);


ALTER TABLE M2OU_EMP ADD CONSTR

create table M2OU_DEPT
(
    ID NUMBER(10) not null
        primary key,
    NAME VARCHAR2(255 char)
);


create table M2OU_EMP
(
    ID NUMBER(6) not null
        primary key,
    NAME VARCHAR2(55) not null,
    DEPARTMENT_ID NUMBER(4)
);

ALTER TABLE M2OU_EMP ADD CONSTRAINT M2OU_EMP_CC_DEPT_FK FOREIGN KEY(DEPARTMENT_ID)  REFERENCES M2OU_DEPT(ID);



