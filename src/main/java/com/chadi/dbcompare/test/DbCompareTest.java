package com.chadi.dbcompare.test;

import com.chadi.dbcompare.bean.DbaTabCols;
import com.chadi.dbcompare.bean.DbaTables;
import com.chadi.dbcompare.bean.UserTriggers;
import com.chadi.dbcompare.dao.DbaTabColsMapper;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DbCompareTest {

    final Logger logger = LoggerFactory.getLogger(DbCompareTest.class);


	@Test
	public void test() throws IOException {
        String constantCol = PropertyUtils.getProperty("Dba_tables.ConsCols_1");
        Map compareMap = CompareUtils.getPropertyToMap("Dba_tables.ConsCols_2");
        logger.info(compareMap.toString());

		String a = CompareUtils.strTrimLowlineAndRenameHump("TABLE_NAME_");
		logger.info(a);


		//库表字段
		DbaTabColsMapper dbaCols_Db1 = MapperFactory.createMapper(DbaTabColsMapper.class, DataSourceEnum.d1);
		DbaTabColsMapper dbaCols_Db2 = MapperFactory.createMapper(DbaTabColsMapper.class, DataSourceEnum.d2);

		Map dbaCols_BaseMap = CompareUtils.getPropertyToMap("Dba_tab_cols.ConsCols_1");
		Map dbaCols_CompareMap = CompareUtils.getPropertyToMap("Dba_tab_cols.ConsCols_2");
		List<String> dbaCols_CompareCols = CompareUtils.getPropertyToList("Dba_tab_cols.ConsCols");

		List<DbaTabCols> dbaCols_BaseList = dbaCols_Db1.getDba_tab_colsByPros(dbaCols_BaseMap);
		List<DbaTabCols> dbaCols_TargetList = dbaCols_Db2.getDba_tab_colsByPros(dbaCols_CompareMap);
		System.out.println(1);

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

	    //两个比较库 共有表名List
        List<String> sameDbaTableName = new ArrayList<>();

        //库表
        DbaTablesMapper dbaTables_db1 = MapperFactory.createMapper(DbaTablesMapper.class, DataSourceEnum.d1);
        DbaTablesMapper dbaTables_db2 = MapperFactory.createMapper(DbaTablesMapper.class, DataSourceEnum.d2);
        Map dbaTables_BaseMap = CompareUtils.getPropertyToMap("Dba_tables.ConsCols_1");
        Map dbaTables_CompareMap = CompareUtils.getPropertyToMap("Dba_tables.ConsCols_2");
        List<String> dbaTables_CompareCols = CompareUtils.getPropertyToList("Dba_tables.ConsCols");
        List<DbaTables> dbaTables_BaseList = dbaTables_db1.getDba_tablesByPros(dbaTables_BaseMap);
        List<DbaTables> dbaTables_TargetList = dbaTables_db2.getDba_tablesByPros(dbaTables_CompareMap);

        //比较方法
        Map<String, List> resultMap = CompareUtils.compareList(dbaTables_BaseList, dbaTables_TargetList, dbaTables_CompareCols);

        //输出匹配结果
		for (DbaTables dbaTables : dbaTables_TargetList) {
			logger.info("对比表名：");
		}


        //匹配成功的数据
        for (DbaTables targetDbaTables : dbaTables_TargetList) {
            //表名缺失
            if (!targetDbaTables.isMatchFlag()) {
                logger.info(targetDbaTables.getTableName());
            }else{
                //表名都有的
				sameDbaTableName.add(targetDbaTables.getTableName());
            }
        }

        logger.info(sameDbaTableName.toString());
        //sameDbaTableName = Arrays.asList("AXTAAEXM", "ZGXMONPM", "BATADBET", "BDPEXCHM");

        //库表字段
        DbaTabColsMapper dbaCols_Db1 = MapperFactory.createMapper(DbaTabColsMapper.class, DataSourceEnum.d1);
        DbaTabColsMapper dbaCols_Db2 = MapperFactory.createMapper(DbaTabColsMapper.class, DataSourceEnum.d2);

        Map dbaCols_BaseMap = CompareUtils.getPropertyToMap("Dba_tab_cols.ConsCols_1");
        Map dbaCols_CompareMap = CompareUtils.getPropertyToMap("Dba_tab_cols.ConsCols_2");

        //每次循环一个表名
        for (String sameTableName : sameDbaTableName) {
            dbaCols_BaseMap.put("TABLE_NAME", sameTableName);
            dbaCols_CompareMap.put("TABLE_NAME", sameTableName);

            List<String> dbaCols_CompareCols = CompareUtils.getPropertyToList("Dba_tab_cols.ConsCols");

            List<DbaTabCols> dbaCols_BaseList = dbaCols_Db1.getDba_tab_colsByPros(dbaCols_BaseMap);
            List<DbaTabCols> dbaCols_TargetList = dbaCols_Db2.getDba_tab_colsByPros(dbaCols_CompareMap);

            //比较方法
            dbaCols_TargetList = (List<DbaTabCols>) CompareUtils.compareList(dbaCols_BaseList, dbaCols_TargetList, dbaCols_CompareCols);

            //匹配成功的数据
            for (DbaTabCols targetDbaTabCols : dbaCols_TargetList) {
                if (!targetDbaTabCols.isMatchFlag()) {
                    logger.info(targetDbaTabCols.getTableName());
                }
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
		Map map = CompareUtils.getPropertyToMap("Dba_tables.ConsCols_1");
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
