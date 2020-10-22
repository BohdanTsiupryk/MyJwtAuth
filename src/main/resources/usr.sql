create table USR
(
    ID NUMBER(19) not null primary key,
    CREATED_AT TIMESTAMP(6),
    EMAIL VARCHAR2(255 char) constraint UK_USR_EMAIL unique,
    FIRST_NAME VARCHAR2(255 char),
    LAST_NAME VARCHAR2(255 char),
    PASSWORD VARCHAR2(255 char)
)
/
create sequence HIBERNATE_SEQUENCE
/
