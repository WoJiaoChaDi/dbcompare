
DROP TABLE tbl_employee;

CREATE TABLE tbl_employee (
  ID NUMBER(10) NOT NULL,
  last_name NVARCHAR2(20),
  gender CHAR(1),
  email NVARCHAR2(220),
  d_id NVARCHAR2(20)
);

alter table tbl_employee
add constraint PK_tbl_employee primary key (ID);