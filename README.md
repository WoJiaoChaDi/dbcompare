# Oracle数据库比较工具

利用Oracle自带的库表结构相关表（Dba_tables\Dba_tab_cols等），来比较数据库的以下内容：
1. 库表
2. 表字段
3. 索引
4. 约束
5. 函数
6. 存储过程

基础配置：
1. 在dbconfig.properties中，配置db1、db2的数据库地址
2. 调整compare.properties中，数据库用户名参数 ConsCols_1\ConsCols_1
3. 调整compare.properties中,每种类型需要匹配的列参数  CompareCols