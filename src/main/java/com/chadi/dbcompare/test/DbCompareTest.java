package com.chadi.dbcompare.test;

import com.chadi.dbcompare.bean.Dba_tab_cols;
import com.chadi.dbcompare.bean.Dba_tables;
import com.chadi.dbcompare.dao.Dba_tab_colsMapper;
import com.chadi.dbcompare.dao.Dba_tablesMapper;
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

        String constantCol = PropertyUtils.getProperty("Dba_tables.constantCol");
        Map compareMap = CompareUtils.getPropertyToMap("Dba_tables.constantCol");
        logger.info(compareMap.toString());

	}



	//查询Dba_tab_cols
	@Test
	public void test_Dba_tab_clos_MapperFactory() throws IOException {

		Dba_tab_colsMapper mapper = MapperFactory.createMapper(Dba_tab_colsMapper.class, DataSourceEnum.d1);

		//通过OWNER查询
		//List<Dba_tables> dba_tablesList = mapper.getDba_tablesByOwner("HEAD");

		//通过配置查询
		Map map = CompareUtils.getPropertyToMap("Dba_tab_cols.constantCol");
		List<Dba_tab_cols> dba_tablesList = mapper.getDba_tab_colsByOwner(map);

		logger.info(""+dba_tablesList.size());

		for (int i = 0; i < 5; i++) {
			Dba_tab_cols result = dba_tablesList.get(i);
			System.out.println(result.toString());
		}
	}


    //Dba_tables
	@Test
	public void test_Dba_tables_MapperFactory() throws IOException {

		Dba_tablesMapper mapper = MapperFactory.createMapper(Dba_tablesMapper.class, DataSourceEnum.d1);

		//通过OWNER查询
		//List<Dba_tables> dba_tablesList = mapper.getDba_tablesByOwner("HEAD");

		//通过配置查询
		Map map = CompareUtils.getPropertyToMap("Dba_tables.constantCol");
		List<Dba_tables> dba_tablesList = mapper.getDba_tablesByPros(map);

		logger.info(""+dba_tablesList.size());

		//for (int i = 0; i < 10; i++) {
		//	Dba_tables result = dba_tablesList.get(i);
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
			Dba_tablesMapper mapper = openSession.getMapper(Dba_tablesMapper.class);
			List<Dba_tables> dba_tablesList= mapper.getDba_tablesByOwner("HEAD");
			System.out.println(mapper.getClass());

			for (int i = 0; i < 10; i++) {
				Dba_tables dba_tables = dba_tablesList.get(i);
				System.out.println(dba_tables.toString());
			}

		} finally {
			openSession.close();
		}
	}

}
