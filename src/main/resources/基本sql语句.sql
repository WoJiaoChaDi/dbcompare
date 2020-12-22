
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




-- 存储过程测试

CREATE OR REPLACE PROCEDURE proce_01(i IN NUMBER) AS
BEGIN
       dbms_output.put_line('12334233');
END proce_01;

CREATE OR REPLACE PROCEDURE proce_02 IS
  x NUMBER(4,2);
  y NUMBER(2,4);
BEGIN
  x := 1;
  y := 2;
  --要处理的业务逻辑
END;

CREATE OR REPLACE PROCEDURE proce_03 IS
BEGIN
  dbms_output.put_line('123');
    --要处理的业务逻辑
--  EXCEPTION    --存储过程异常
END;

CREATE OR REPLACE PROCEDURE proce_04 IS
BEGIN
  dbms_output.put_line('123aaaaa');
    --要处理的业务逻辑
--  EXCEPTION    --存储过程异常
END;

CREATE OR REPLACE PROCEDURE proce_05 IS
BEGIN
  dbms_output.put_line('123aaaa55a');
    --要处理的业务逻辑
--  EXCEPTION    --存储过程异常
END;


-- 触发器比较
CREATE OR REPLACE TRIGGER trigger_01
BEFORE INSERT ON AXTAAEXM
FOR EACH ROW
  DECLARE
  next_id NUMBER;
  BEGIN
    dbms_output.put_line('1');
  END;

CREATE OR REPLACE TRIGGER trigger_02
BEFORE INSERT ON AXTAAEXM
FOR EACH ROW
DECLARE
next_id NUMBER;
BEGIN
  dbms_output.put_line('2');
END;


CREATE OR REPLACE TRIGGER trigger_03
BEFORE INSERT ON AXTAAEXM
FOR EACH ROW
  DECLARE
  next_id NUMBER;
  BEGIN
    dbms_output.put_line('3');
  END;


--方法比较
CREATE OR REPLACE FUNCTION fun_01(i IN NUMBER) AS
BEGIN
       dbms_output.put_line('1');
END proce_01;

CREATE OR REPLACE FUNCTION fun_02(i IN NUMBER) AS
BEGIN
       dbms_output.put_line('123');
END proce_01;

CREATE OR REPLACE FUNCTION fun_03(i IN NUMBER) AS
BEGIN
       dbms_output.put_line('3');
END proce_01;

CREATE OR REPLACE FUNCTION fun_04(i IN NUMBER) AS
BEGIN
       dbms_output.put_line('4');
END proce_01;

CREATE OR REPLACE FUNCTION fun_05(i IN NUMBER) AS
BEGIN
       dbms_output.put_line('5');
END proce_01;




