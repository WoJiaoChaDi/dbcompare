package com.chadi.dbcompare.test;

import com.alibaba.druid.util.StringUtils;
import com.chadi.dbcompare.bean.Dba_tables;
import com.chadi.dbcompare.dao.Dba_tablesMapper;
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

public class DbCompareTest {
	
	@Test
	public void test_MapperFactory() throws IOException {

		final Logger logger = LoggerFactory.getLogger(DbCompareTest.class);

		Dba_tablesMapper mapper = MapperFactory.createMapper(Dba_tablesMapper.class, DataSourceEnum.d1);

		List<Dba_tables> dba_tablesList = mapper.getDba_tablesByOwner("HEAD");

		logger.info(""+dba_tablesList.size());

		//for (int i = 0; i < 10; i++) {
		//	Dba_tables dba_tables = dba_tablesList.get(i);
		//	System.out.println(dba_tables.toString());
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
