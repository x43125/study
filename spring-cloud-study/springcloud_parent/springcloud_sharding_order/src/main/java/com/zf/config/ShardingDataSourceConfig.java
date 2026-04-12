package com.zf.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * ShardingSphere数据源配置类
 * 
 * 当使用sharding profile时（spring.profiles.active=sharding），
 * 使用此配置类，由ShardingSphere直接管理数据源
 * 
 * @author zf
 * @since 2026-04-12
 */
@Configuration
@ConditionalOnProperty(name = "spring.profiles.active", havingValue = "sharding")
@MapperScan("com.zf.mapper")
public class ShardingDataSourceConfig {

    /**
     * 配置SqlSessionFactory
     * 
     * @param dataSource ShardingSphere数据源（由Spring Boot自动配置）
     * @return SqlSessionFactory
     * @throws Exception 异常
     */
    @Bean
    @Primary
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);

        // 设置MyBatis配置
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setLogImpl(org.apache.ibatis.logging.stdout.StdOutImpl.class);
        sessionFactory.setConfiguration(configuration);

        // 设置Mapper扫描路径
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactory.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));

        // 设置类型别名包
        sessionFactory.setTypeAliasesPackage("com.zf.entity");

        return sessionFactory.getObject();
    }

    /**
     * 配置事务管理器
     * 
     * @param dataSource 数据源
     * @return 事务管理器
     */
    @Bean
    @Primary
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}