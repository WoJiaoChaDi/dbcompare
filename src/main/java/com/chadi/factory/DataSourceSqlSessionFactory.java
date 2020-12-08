package com.chadi.factory;

import com.chadi.dbcompare.dao.Mapper;
import org.apache.commons.io.IOUtils;
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

public final class DataSourceSqlSessionFactory {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(DataSourceSqlSessionFactory.class);
    private static final String CONFIGURATION_PATH = "mybatis-config.xml";

    private static final Map<DataSourceEnum, SqlSessionFactory> SQLSESSIONFACTORYS
            = new HashMap<DataSourceEnum, SqlSessionFactory>();

    /**
     * 根据指定的DataSourceEnvironment获取对应的SqlSessionFactory
     * @param environment 数据源environment
     * @return SqlSessionFactory
     */
    public static SqlSessionFactory getSqlSessionFactory(DataSourceEnum environment) {

        SqlSessionFactory sqlSessionFactory = SQLSESSIONFACTORYS.get(environment);
        if (!ObjectUtils.isEmpty(sqlSessionFactory))
            return sqlSessionFactory;
        else {
            InputStream inputStream = null;
            try {
                inputStream = Resources.getResourceAsStream(CONFIGURATION_PATH);
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream, environment.name());

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

    public static <T> T getTypeMapper(DataSourceEnum environment, Class<? extends Mapper> clazz){
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory(environment);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        return (T) sqlSession.getMapper(clazz);
    }
}