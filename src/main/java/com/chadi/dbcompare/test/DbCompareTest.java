package com.chadi.dbcompare.test;

import com.chadi.dbcompare.bean.DbaIndColumns;
import com.chadi.dbcompare.bean.DbaTabCols;
import com.chadi.dbcompare.bean.DbaTables;
import com.chadi.dbcompare.bean.UserConstraints;
import com.chadi.dbcompare.bean.UserIndexes;
import com.chadi.dbcompare.bean.UserTriggers;
import com.chadi.dbcompare.dao.DbaIndColumnsMapper;
import com.chadi.dbcompare.dao.DbaTabColsMapper;
import com.chadi.dbcompare.dao.DbaTablesMapper;
import com.chadi.dbcompare.dao.UserConstraintsMapper;
import com.chadi.dbcompare.dao.UserIndexesMapper;
import com.chadi.dbcompare.dao.UserTriggersMapper;
import com.chadi.dbcompare.utils.CommonUtils;
import com.chadi.dbcompare.utils.CompareUtils;
import com.chadi.dbcompare.utils.PropertyUtils;
import com.chadi.factory.DataSourceEnum;
import com.chadi.factory.DataSourceSqlSessionFactory;
import com.chadi.factory.MapperFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class DbCompareTest {

    final Logger logger = LoggerFactory.getLogger(DbCompareTest.class);


	@Test
	public void test() throws IOException {
        String constantCol = PropertyUtils.getProperty("Dba_tables.ConsCols_1");
        Map compareMap = PropertyUtils.getPropertyToMap("Dba_tables.ConsCols_2");
        logger.info(compareMap.toString());

		String a = CommonUtils.strTrimLowlineAndRenameHump("TABLE_NAME_");
		logger.info(a);


		//这种方式，每次使用完Mapper会关掉SqlSessionFactory连接
        //DbaTablesMapper dbaCols_Db1 = MapperFactory.createMapper(DbaTablesMapper.class, DataSourceEnum.d1);
		//Map dbaCols_BaseMap = PropertyUtils.getPropertyToMap("Dba_tables.ConsCols_1");
		//List<DbaTables> dbaCols_BaseList = dbaCols_Db1.getDba_tablesByPros(dbaCols_BaseMap);
        //logger.info("=============");
        //List<DbaTables> dbaCols_BaseList2 = dbaCols_Db1.getDba_tablesByOwner("HEAD");


        //这种方式保持了SqlSessionFactory连接
        SqlSessionFactory sqlSessionFactory = DataSourceSqlSessionFactory.getSqlSessionFactory(DataSourceEnum.d1);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        DbaTablesMapper dbaCols_Db1 = sqlSession.getMapper(DbaTablesMapper.class);
        Map dbaCols_BaseMap = PropertyUtils.getPropertyToMap("Dba_tables.ConsCols_1");
        List<DbaTables> dbaCols_BaseList = dbaCols_Db1.getDba_tablesByPros(dbaCols_BaseMap, null, null);
        logger.info("=============");
        List<DbaTables> dbaCols_BaseList2 = dbaCols_Db1.getDba_tablesByOwner("HEAD");

    }


    /**
     * @description: 比较器测试 - 约束
     * @param
     * @return: void
     * @author: XuDong
     * @time: 2020/12/8 10:32
     */
    @Test
    public void test_Constraints() throws IOException, IllegalAccessException {
        UserConstraintsMapper uConstr_db1 = DataSourceSqlSessionFactory.getTypeMapper(DataSourceEnum.d1, UserConstraintsMapper.class);
        UserConstraintsMapper uConstr_db2 = DataSourceSqlSessionFactory.getTypeMapper(DataSourceEnum.d1, UserConstraintsMapper.class);
        Map uConstr_BaseMap = PropertyUtils.getPropertyToMap("User_Constraints.ConsCols_1");
        Map uConstr_CompareMap = PropertyUtils.getPropertyToMap("User_Constraints.ConsCols_2");
        Map uConstr_NotLike = PropertyUtils.getPropertyToMap("User_Constraints.NotLikeMap");
        List<String> uConstr_JoinPlusList = PropertyUtils.getPropertyToList("User_Constraints.JoinPlus");
        List<String> uConstr_CompareCols = PropertyUtils.getPropertyToList("User_Constraints.ConsCols");

        List<UserConstraints> uConstr_BaseList = uConstr_db1.getUser_ConstraintsByPros(uConstr_BaseMap, uConstr_NotLike, uConstr_JoinPlusList);
        List<UserConstraints> uConstr_TargetList = uConstr_db2.getUser_ConstraintsByPros(uConstr_CompareMap, uConstr_NotLike, uConstr_JoinPlusList);

        //比较方法
        Map<String, List> resultMap = CompareUtils.compareList(uConstr_BaseList, uConstr_TargetList, uConstr_CompareCols);

        //输出结果
        CompareUtils.soutResult(resultMap, "uConstr", uConstr_CompareCols);
    }


    /**
     * @description: 比较器测试 - 索引
     * @param
     * @return: void
     * @author: XuDong
     * @time: 2020/12/8 10:32
     */
    @Test
    public void test_Indexs() throws IOException, IllegalAccessException {
        DbaIndColumnsMapper dbaIndex_db1 = DataSourceSqlSessionFactory.getTypeMapper(DataSourceEnum.d1, DbaIndColumnsMapper.class);
        DbaIndColumnsMapper dbaIndex_db2 = DataSourceSqlSessionFactory.getTypeMapper(DataSourceEnum.d1, DbaIndColumnsMapper.class);
        Map dbaIndex_BaseMap = PropertyUtils.getPropertyToMap("Dba_ind_columns.ConsCols_1");
        Map dbaIndex_CompareMap = PropertyUtils.getPropertyToMap("Dba_ind_columns.ConsCols_2");
        Map dbaIndex_NotLikeMap = PropertyUtils.getPropertyToMap("Dba_ind_columns.NotLikeMap");
        List<String> dbaIndex_JoinPlusList = PropertyUtils.getPropertyToList("Dba_ind_columns.JoinPlus");
        List<String> dbaIndex_CompareCols = PropertyUtils.getPropertyToList("Dba_ind_columns.ConsCols");

        List<DbaIndColumns> dbaIndex_BaseList = dbaIndex_db1.getDba_ind_columnsByPros(dbaIndex_BaseMap, dbaIndex_NotLikeMap, dbaIndex_JoinPlusList);
        List<DbaIndColumns> dbaIndex_TargetList = dbaIndex_db2.getDba_ind_columnsByPros(dbaIndex_CompareMap, dbaIndex_NotLikeMap, dbaIndex_JoinPlusList);

        //比较方法
        Map<String, List> resultMap = CompareUtils.compareList(dbaIndex_BaseList, dbaIndex_TargetList, dbaIndex_CompareCols);

        //输出结果
        CompareUtils.soutResult(resultMap, "dbaIndex", dbaIndex_CompareCols);


        logger.info("===================");

        UserIndexesMapper uIndex_db1 = DataSourceSqlSessionFactory.getTypeMapper(DataSourceEnum.d1, UserIndexesMapper.class);
        UserIndexesMapper uIndex_db2 = DataSourceSqlSessionFactory.getTypeMapper(DataSourceEnum.d1, UserIndexesMapper.class);
        Map uIndex_BaseMap = PropertyUtils.getPropertyToMap("User_indexes.ConsCols_1");
        Map uIndex_CompareMap = PropertyUtils.getPropertyToMap("User_indexes.ConsCols_2");
        Map uIndex_NotLikeMap = PropertyUtils.getPropertyToMap("User_indexes.NotLikeMap");
        List<String> uIndex_JoinPlusList = PropertyUtils.getPropertyToList("User_indexes.JoinPlus");

        List<String> uIndex_CompareCols = PropertyUtils.getPropertyToList("User_indexes.ConsCols");
        List<UserIndexes> uIndex_BaseList = uIndex_db1.getUser_indexesByPros(uIndex_BaseMap, uIndex_NotLikeMap, uIndex_JoinPlusList);
        List<UserIndexes> uIndex_TargetList = uIndex_db2.getUser_indexesByPros(uIndex_CompareMap, uIndex_NotLikeMap, uIndex_JoinPlusList);

        //比较方法
        Map<String, List> uIndex_resultMap = CompareUtils.compareList(uIndex_BaseList, uIndex_TargetList, uIndex_CompareCols);

        //输出结果
        CompareUtils.soutResult(uIndex_resultMap, "uIndex", uIndex_CompareCols);
    }


	/**
	 * @description: 比较器测试 - 表与列测试
	 * @param
	 * @return: void
	 * @author: XuDong
	 * @time: 2020/12/7 15:01
	 */
	@Test
	public void test_TableAndCols() throws IOException, IllegalAccessException {

        //库表
        //DbaTablesMapper dbaTables_db1 = MapperFactory.createMapper(DbaTablesMapper.class, DataSourceEnum.d1);
        //DbaTablesMapper dbaTables_db2 = MapperFactory.createMapper(DbaTablesMapper.class, DataSourceEnum.d2);

        DbaTablesMapper dbaTables_db1 = DataSourceSqlSessionFactory.getTypeMapper(DataSourceEnum.d1, DbaTablesMapper.class);
        DbaTablesMapper dbaTables_db2 = DataSourceSqlSessionFactory.getTypeMapper(DataSourceEnum.d1, DbaTablesMapper.class);

        Map dbaTables_BaseMap = PropertyUtils.getPropertyToMap("Dba_tables.ConsCols_1");
        Map dbaTables_CompareMap = PropertyUtils.getPropertyToMap("Dba_tables.ConsCols_2");
        Map dbaTables_NotLikeMap = PropertyUtils.getPropertyToMap("Dba_tables.NotLikeMap");
        List<String> dbaTables_JoinPlusList = PropertyUtils.getPropertyToList("Dba_tables.JoinPlus");
        List<String> dbaTables_CompareCols = PropertyUtils.getPropertyToList("Dba_tables.ConsCols");


        List<DbaTables> dbaTables_BaseList = dbaTables_db1.getDba_tablesByPros(dbaTables_BaseMap, dbaTables_NotLikeMap, dbaTables_JoinPlusList);
        List<DbaTables> dbaTables_TargetList = dbaTables_db2.getDba_tablesByPros(dbaTables_CompareMap, dbaTables_NotLikeMap, dbaTables_JoinPlusList);

        //比较方法
        Map<String, List> resultMap = CompareUtils.compareList(dbaTables_BaseList, dbaTables_TargetList, dbaTables_CompareCols);

        //输出结果
        CompareUtils.soutResult(resultMap, "tableName", dbaTables_CompareCols);

        List<Map> baseMapNoMatchList = resultMap.get("baseMapNoMatchList");
        List<Map> baseMapMatchList = resultMap.get("baseMapMatchList");
        //库表字段
        DbaTabColsMapper dbaCols_Db1 = DataSourceSqlSessionFactory.getTypeMapper(DataSourceEnum.d1, DbaTabColsMapper.class);
        DbaTabColsMapper dbaCols_Db2 = DataSourceSqlSessionFactory.getTypeMapper(DataSourceEnum.d1, DbaTabColsMapper.class);

        Map dbaCols_BaseMap = PropertyUtils.getPropertyToMap("Dba_tab_cols.ConsCols_1");
        Map dbaCols_CompareMap = PropertyUtils.getPropertyToMap("Dba_tab_cols.ConsCols_2");
        Map dbaCols_NotLikeMap = PropertyUtils.getPropertyToMap("Dba_tab_cols.NotLikeMap");
        List<String> dbaCols_JoinPlusList = PropertyUtils.getPropertyToList("Dba_tab_cols.JoinPlus");

        //每次循环一个表名 匹配列
        for (Map baseMapMatch : baseMapMatchList) {

            String sameTableName = (String) baseMapMatch.get("tableName");
            dbaCols_BaseMap.put("TABLE_NAME", sameTableName);
            dbaCols_CompareMap.put("TABLE_NAME", sameTableName);

            List<String> dbaCols_CompareCols = PropertyUtils.getPropertyToList("Dba_tab_cols.ConsCols");

            List<DbaTabCols> dbaCols_BaseList = dbaCols_Db1.getDba_tab_colsByPros(dbaCols_BaseMap, dbaCols_NotLikeMap, dbaCols_JoinPlusList);
            List<DbaTabCols> dbaCols_TargetList = dbaCols_Db2.getDba_tab_colsByPros(dbaCols_CompareMap, dbaCols_NotLikeMap, dbaCols_JoinPlusList);

            //比较方法
            Map<String, List> resultColMap = CompareUtils.compareList(dbaCols_BaseList, dbaCols_TargetList, dbaCols_CompareCols);

            //输出结果
            logger.info("=============当前处理的表名是：" + baseMapMatch.get("tableName") + "=============");
            CompareUtils.soutResult(resultColMap, "columnName", dbaCols_CompareCols);
        }

	}

    //查询Dba_tab_cols
	@Test
	public void test_Dba_tab_clos_MapperFactory() throws IOException {

		//表
		//DbaTablesMapper mapper = MapperFactory.createMapper(DbaTablesMapper.class, DataSourceEnum.d1);
		//Map map = PropertyUtils.getPropertyToMap("DbaTables.constantCol");
		//List<DbaTables> resultList = mapper.getDba_tablesByPros(map);

		//表列
		//DbaTabColsMapper mapper = MapperFactory.createMapper(DbaTabColsMapper.class, DataSourceEnum.d1);
		//Map map = PropertyUtils.getPropertyToMap("DbaTabCols.constantCol");
		//List<DbaTabCols> resultList = mapper.getDba_tab_colsByPros(map);

		//索引
		//DbaIndColumnsMapper mapper = MapperFactory.createMapper(DbaIndColumnsMapper.class, DataSourceEnum.d1);
		//Map map = PropertyUtils.getPropertyToMap("DbaIndColumns.constantCol");
		//List<DbaIndColumns> resultList = mapper.getDba_ind_columnsByPros(map);

		//索引
		//UserIndexesMapper mapper = MapperFactory.createMapper(UserIndexesMapper.class, DataSourceEnum.d1);
		//Map map = PropertyUtils.getPropertyToMap("UserIndexes.constantCol");
		//List<UserIndexes> resultList = mapper.getUser_indexesByPros(map);

		//约束
		//UserConstraintsMapper mapper = MapperFactory.createMapper(UserConstraintsMapper.class, DataSourceEnum.d1);
		//Map map = PropertyUtils.getPropertyToMap("UserConstraints.constantCol");
		//List<UserConstraints> resultList = mapper.getUser_ConstraintsByPros(map);

		//存储过程与函数
		//UserProceduresMapper mapper = MapperFactory.createMapper(UserProceduresMapper.class, DataSourceEnum.d1);
		//Map map = PropertyUtils.getPropertyToMap("UserProcedures.constantCol");
		//List<UserProcedures> resultList = mapper.getUser_proceduresByPros(map);

		//触发器
		UserTriggersMapper mapper = MapperFactory.createMapper(UserTriggersMapper.class, DataSourceEnum.d1);
		Map map = PropertyUtils.getPropertyToMap("User_triggers.constantCol");
		List<UserTriggers> resultList = mapper.getUser_triggersByPros(map);

		logger.info(""+resultList.size());

		//for (int i = 0; i < 5; i++) {
		//	DbaTabCols result = dba_tablesList.get(i);
		//	System.out.println(result.toString());
		//}
	}


    //DbaTables
	@Test
	public void test_Dba_tables_MapperFactory() throws IOException {

		DbaTablesMapper mapper = MapperFactory.createMapper(DbaTablesMapper.class, DataSourceEnum.d1);

		//通过OWNER查询
		//List<DbaTables> dba_tablesList = mapper.getDba_tablesByOwner("HEAD");

		//通过配置查询
		Map map = PropertyUtils.getPropertyToMap("Dba_tables.ConsCols_1");
		List<DbaTables> dba_tablesList = mapper.getDba_tablesByPros(map, null, null);

		logger.info(""+dba_tablesList.size());

		//for (int i = 0; i < 10; i++) {
		//	DbaTables result = dba_tablesList.get(i);
		//	System.out.println(result.toString());
		//}
	}


	@Test
	public void test_Dba_tables() throws IOException {
		// 1、获取sqlSessionFactory对象
		SqlSessionFactory sqlSessionFactory = DataSourceSqlSessionFactory.getSqlSessionFactory(DataSourceEnum.d2);
		// 2、获取sqlSession对象
		SqlSession openSession = sqlSessionFactory.openSession();
		try {
			// 3、获取接口的实现类对象
			//会为接口自动的创建一个代理对象，代理对象去执行增删改查方法
			DbaTablesMapper mapper = openSession.getMapper(DbaTablesMapper.class);
			List<DbaTables> dba_tablesList= mapper.getDba_tablesByOwner("HEAD");
			System.out.println(mapper.getClass());

			for (int i = 0; i < 10; i++) {
				DbaTables dba_tables = dba_tablesList.get(i);
				System.out.println(dba_tables.toString());
			}

		} finally {
			openSession.close();
		}
	}

}
