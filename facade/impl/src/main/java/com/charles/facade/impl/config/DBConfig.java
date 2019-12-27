package com.charles.facade.impl.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.charles.common.dal.DBSwitch;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import tk.mybatis.spring.annotation.MapperScan;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackageClasses = DBSwitch.class)
@SuppressWarnings("all")
public class DBConfig {

    @Bean
    public DataSource dataSource(@Value("${jdbc.driver}") String driverClazz, @Value("${jdbc.url}") String url, @Value("${jdbc.username}") String userName, @Value("${jdbc.password}") String password,
                                 @Value("${jdbc.maxactive}") int maxActive, @Value("${jdbc.maxWait}") int maxWait, @Value("${jdbc.validationQuery}") String validateQuery,
                                 @Value("${jdbc.testOnBorrow}") boolean testOnBorrow, @Value("${jdbc.testWhileIdle}") boolean testWhileIdle,
                                 @Value("${jdbc.timeBetweenEvictionRunsMillis}") int timeBetweenEvictionRunsMillis, @Value("${jdbc.minEvictableIdleTimeMillis}") int minEvictableIdleTimeMillis,
                                 @Value("${jdbc.poolPreparedStatements}") boolean poolPreparedStatements,
                                 @Value("${jdbc.maxPoolPreparedStatementPerConnectionSize}") int maxPoolPreparedStatementPerConnectionSize) {
        DruidDataSource ds = new DruidDataSource();
        ds.setDriverClassName(driverClazz);
        ds.setUrl(url);
        ds.setUsername(userName);
        ds.setPassword(password);
        ds.setMaxActive(maxActive);
        ds.setMaxWait(maxWait);
        ds.setValidationQuery(validateQuery);
        ds.setTestOnBorrow(testOnBorrow);
        ds.setTestWhileIdle(testWhileIdle);
        ds.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        ds.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        ds.setPoolPreparedStatements(poolPreparedStatements);
        ds.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
        return ds;
    }

    @Bean
    public DataSourceTransactionManager transactionManager(@Qualifier("dataSource") DataSource ds) {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(ds);
        return transactionManager;
    }
}