package com.chadi.dbcompare.test;

import com.chadi.dbcompare.bean.Dba_tables;
import com.chadi.dbcompare.dao.Dba_tablesMapper;
import com.chadi.factory.DataSourceEnum;
import com.chadi.factory.DataSourceSqlSessionFactory;
import com.chadi.factory.MapperFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * 1、接口式编程
 * 	原生：		Dao		====>  DaoImpl
 * 	mybatis：	Mapper	====>  xxMapper.xml
 * 
 * 2、SqlSession代表和数据库的一次会话；用完必须关闭；
 * 3、SqlSession和connection一样她都是非线程安全。每次使用都应该去获取新的对象。
 * 4、mapper接口没有实现类，但是mybatis会为这个接口生成一个代理对象。
 * 		（将接口和xml进行绑定）
 * 		EmployeeMapper empMapper =	sqlSession.getMapper(EmployeeMapper.class);
 * 5、两个重要的配置文件：
 * 		mybatis的全局配置文件：包含数据库连接池信息，事务管理器信息等...系统运行环境信息
 * 		sql映射文件：保存了每一个sql语句的映射信息：
 * 					将sql抽取出来。	
 * 
 * 
 * @author lfy
 *
 */
public class DbCompareTest {
	
	@Test
	public void test_MapperFactory() throws IOException {

		Dba_tablesMapper mapper = MapperFactory.createMapper(Dba_tablesMapper.class, DataSourceEnum.d1);

		List<Dba_tables> dba_tablesList = mapper.getDba_tablesByOwner("HEAD");
		System.out.println(mapper.getClass());

		for (int i = 0; i < 10; i++) {
			Dba_tables dba_tables = dba_tablesList.get(i);
			System.out.println(dba_tables.toString());
		}
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
