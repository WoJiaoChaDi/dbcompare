package com.chadi.dbcompare.test;

import com.chadi.dbcompare.bean.DbaTables;
import com.chadi.dbcompare.bean.UserTriggers;
import com.chadi.dbcompare.dao.DbaTablesMapper;
import com.chadi.dbcompare.dao.UserTriggersMapper;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DbCompareTest {

    final Logger logger = LoggerFactory.getLogger(DbCompareTest.class);


	@Test
	public void test() throws IOException {
		Map map = new HashMap<>();

        String constantCol = PropertyUtils.getProperty("Dba_tables.constantCol_HEAD");
        Map compareMap = CompareUtils.getPropertyToMap("Dba_tables.constantCol_XD_TEST_COMPARE");
        logger.info(compareMap.toString());

		String a = CompareUtils.strTrimLowlineAndRenameHump("TABLE_NAME_");
		logger.info(a);

	}



	/**
	 * @description: 比较器测试
	 * @param
	 * @return: void
	 * @author: XuDong
	 * @time: 2020/12/7 15:01
	 */
	@Test
	public void test_compare() throws IOException, IllegalAccessException {

		//库
		DbaTablesMapper db1 = MapperFactory.createMapper(DbaTablesMapper.class, DataSourceEnum.d1);
		DbaTablesMapper db2 = MapperFactory.createMapper(DbaTablesMapper.class, DataSourceEnum.d2);

		Map baseMap = CompareUtils.getPropertyToMap("Dba_tables.constantCol_HEAD");
		Map compareMap = CompareUtils.getPropertyToMap("Dba_tables.constantCol_XD_TEST_COMPARE");
		List<String> compareCols = CompareUtils.getPropertyToList("Dba_tables.compareCol");

		List<DbaTables> baseList = db1.getDba_tablesByPros(baseMap);
		List<DbaTables> targetList = db2.getDba_tablesByPros(compareMap);

		targetList = (List<DbaTables>) CompareUtils.compareList(baseList, targetList, compareCols);

		//匹配成功的数据
		for (DbaTables targetDbaTables : targetList) {
			if (targetDbaTables.isMatchFlag()) {
				logger.info(targetDbaTables.getTableName());
			}
		}
	}

	//查询Dba_tab_cols
	@Test
	public void test_Dba_tab_clos_MapperFactory() throws IOException {

		//表
		//DbaTablesMapper mapper = MapperFactory.createMapper(DbaTablesMapper.class, DataSourceEnum.d1);
		//Map map = CompareUtils.getPropertyToMap("DbaTables.constantCol");
		//List<DbaTables> resultList = mapper.getDba_tablesByPros(map);

		//表列
		//DbaTabColsMapper mapper = MapperFactory.createMapper(DbaTabColsMapper.class, DataSourceEnum.d1);
		//Map map = CompareUtils.getPropertyToMap("DbaTabCols.constantCol");
		//List<DbaTabCols> resultList = mapper.getDba_tab_colsByPros(map);

		//索引
		//DbaIndColumnsMapper mapper = MapperFactory.createMapper(DbaIndColumnsMapper.class, DataSourceEnum.d1);
		//Map map = CompareUtils.getPropertyToMap("DbaIndColumns.constantCol");
		//List<DbaIndColumns> resultList = mapper.getDba_ind_columnsByPros(map);

		//索引
		//UserIndexesMapper mapper = MapperFactory.createMapper(UserIndexesMapper.class, DataSourceEnum.d1);
		//Map map = CompareUtils.getPropertyToMap("UserIndexes.constantCol");
		//List<UserIndexes> resultList = mapper.getUser_indexesByPros(map);

		//约束
		//UserConstraintsMapper mapper = MapperFactory.createMapper(UserConstraintsMapper.class, DataSourceEnum.d1);
		//Map map = CompareUtils.getPropertyToMap("UserConstraints.constantCol");
		//List<UserConstraints> resultList = mapper.getUser_ConstraintsByPros(map);

		//存储过程与函数
		//UserProceduresMapper mapper = MapperFactory.createMapper(UserProceduresMapper.class, DataSourceEnum.d1);
		//Map map = CompareUtils.getPropertyToMap("UserProcedures.constantCol");
		//List<UserProcedures> resultList = mapper.getUser_proceduresByPros(map);

		//触发器
		UserTriggersMapper mapper = MapperFactory.createMapper(UserTriggersMapper.class, DataSourceEnum.d1);
		Map map = CompareUtils.getPropertyToMap("User_triggers.constantCol");
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
		Map map = CompareUtils.getPropertyToMap("Dba_tables.constantCol_HEAD");
		List<DbaTables> dba_tablesList = mapper.getDba_tablesByPros(map);

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
