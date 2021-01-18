package com.chadi.factory;

import com.chadi.dbcompare.dao.Mapper;
import org.apache.commons.io.IOUtils;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public final class DataSourceSqlDynamicSessionFactory {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(DataSourceSqlDynamicSessionFactory.class);
    private static final String CONFIGURATION_PATH = "mybatis-config.xml";

    private static final Map<DataSourceEnum, SqlSessionFactory> SQLSESSIONFACTORYS
            = new HashMap<DataSourceEnum, SqlSessionFactory>();

    /**
     * 根据指定的DataSourceEnvironment获取对应的SqlSessionFactory
     * @param environment 数据源environment
     * @return SqlSessionFactory
     */
    public static SqlSessionFactory getSqlSessionFactory(DataSourceEnum environment, SimpleDataSource simpleDataSource) {

        SqlSessionFactory sqlSessionFactory = SQLSESSIONFACTORYS.get(environment);
        if (!ObjectUtils.isEmpty(sqlSessionFactory))
            return sqlSessionFactory;
        else {
            InputStream inputStream = null;
            try {
                inputStream = Resources.getResourceAsStream(CONFIGURATION_PATH);
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream, environment.name());

                //截取DataSource，更改账户
                PooledDataSource pooledDataSource = (PooledDataSource)sqlSessionFactory.getConfiguration().getEnvironment().getDataSource();
                pooledDataSource.setUsername(simpleDataSource.getUsername());
                pooledDataSource.setPassword(simpleDataSource.getPassword());
                pooledDataSource.setUrl(simpleDataSource.getUrl());
                pooledDataSource.setDriver(simpleDataSource.getDriver());

                logger.info("Get {} SqlSessionFactory successfully." , environment.name());
            } catch (IOException e) {
                logger.warn("Get {} SqlSessionFactory error.", environment.name());
                logger.error(e.getMessage(), e);
            }
            finally {
                IOUtils.closeQuietly(inputStream);
            }

            SQLSESSIONFACTORYS.put(environment, sqlSessionFactory);
            return sqlSessionFactory;
        }
    }

    public static <T> T getTypeMapper(DataSourceEnum environment, Class<? extends Mapper> clazz, SimpleDataSource simpleDataSource){
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory(environment, simpleDataSource);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        return (T) sqlSession.getMapper(clazz);
    }
}