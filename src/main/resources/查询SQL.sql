
--表名
SELECT t1.* FROM dba_tables t1 WHERE t1.OWNER = 'XD_TEST_COMPARE';
SELECT t1.* FROM dba_tables t1 WHERE t1.OWNER = 'XD_TEST_COMPARE2';

--列名
SELECT t1.* FROM dba_tab_cols t1 WHERE t1.OWNER = 'XD_TEST_COMPARE' AND t1.TABLE_NAME = 'AXTAAEXM';
SELECT t1.* FROM dba_tab_cols t1 WHERE t1.OWNER = 'XD_TEST_COMPARE2' AND t1.TABLE_NAME = 'AXTAAEXM';
SELECT t.* from AXTAAEXM t;

--索引名
SELECT t.* from User_indexes t WHERE t.TABLE_OWNER = 'XD_TEST_COMPARE' AND t.INDEX_NAME NOT LIKE 'BIN%';
SELECT t.* from User_indexes t WHERE t.TABLE_OWNER = 'XD_TEST_COMPARE2' AND t.INDEX_NAME NOT LIKE 'BIN%';
--索引详情
SELECT t.* from Dba_ind_columns t WHERE t.INDEX_OWNER = 'XD_TEST_COMPARE' AND t.INDEX_NAME NOT LIKE 'BIN%';
SELECT t.* from Dba_ind_columns t WHERE t.INDEX_OWNER = 'XD_TEST_COMPARE2' AND t.INDEX_NAME NOT LIKE 'BIN%';

--约束
SELECT t.* from User_Constraints t WHERE t.OWNER = 'XD_TEST_COMPARE' AND t.constraint_name NOT LIKE 'BIN%' AND t.constraint_name NOT LIKE 'SYS_%';
SELECT t.* from User_Constraints t WHERE t.OWNER = 'XD_TEST_COMPARE2' AND t.constraint_name NOT LIKE 'BIN%' AND t.constraint_name NOT LIKE 'SYS_%';
--约束详情
SELECT t.* from dba_cons_columns t WHERE t.owner = 'XD_TEST_COMPARE'  AND t.constraint_name NOT LIKE 'BIN%' AND t.constraint_name NOT LIKE 'SYS_%';
SELECT t.* from dba_cons_columns t WHERE t.owner = 'XD_TEST_COMPARE2'  AND t.constraint_name NOT LIKE 'BIN%' AND t.constraint_name NOT LIKE 'SYS_%';

--函数、存储过程、触发器
SELECT t1.* FROM User_procedures t1;
SELECT t1.* from dba_source t1 WHERE t1.owner = 'XD_TEST_COMPARE';
--函数、存储过程、触发器
SELECT t.* from Dba_Procedures t WHERE t.owner = 'XD_TEST_COMPARE';
SELECT t1.* from dba_source t1 WHERE t1.owner = 'XD_TEST_COMPARE';




SELECT t1.* FROM user_objects t1 WHERE t1.OBJECT_TYPE = 'PROCEDURE';
SELECT t1.* FROM user_source t1 WHERE t1.name = 'TRIGGER_01';

SELECT t1.* FROM User_procedures t1;
SELECT t1.* from dba_source t1 WHERE t1.owner = 'XD_TEST_COMPARE';

SELECT t1.* FROM User_triggers t1;
SELECT t1.* from dba_source t1 WHERE t1.owner = 'XD_TEST_COMPARE';

SELECT t1.* from  t1
SELECT t1.* from dba_source t1;