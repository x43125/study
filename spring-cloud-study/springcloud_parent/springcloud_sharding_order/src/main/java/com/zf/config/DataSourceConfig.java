package com.zf.config;

import com.zf.datasource.DataSourceContextHolder;
import com.zf.datasource.DynamicDataSource;
import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据源配置类
 * 
 * 注意：此配置类仅在未使用sharding profile时生效
 * 当使用sharding profile时，由ShardingDataSourceConfig接管
 * 
 * 功能：
 * 1. 配置老表数据源（普通数据源）
 * 2. 配置动态数据源（用于双写）
 * 3. 配置SqlSessionFactory
 * 4. 配置事务管理器
 * 
 * @author zf
 * @since 2026-04-12
 */
@Configuration
@Conditional(NotShardingProfileCondition.class)
@MapperScan("com.zf.mapper")
public class DataSourceConfig {

    /**
     * 配置老表数据源
     * 
     * @return 老表数据源
     */
    @Bean(name = "oldDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.druid")
    @ConditionalOnProperty(name = "sharding.enabled", havingValue = "true", matchIfMissing = false)
    public DataSource oldDataSource() {
        return new DruidDataSource();
    }

    /**
     * 配置新表数据源（ShardingSphere）
     * 
     * 注意：ShardingSphere数据源由Spring Boot自动配置
     * 只需要在application-sharding.yml中配置即可
     * 
     * @return ShardingSphere数据源
     */
    @Bean(name = "newDataSource")
    @ConditionalOnProperty(name = "spring.profiles.active", havingValue = "sharding")
    public DataSource newDataSource() {
        // ShardingSphere数据源会自动注入
        // 这里只是声明一个Bean，实际由Spring Boot自动配置
        return null;
    }

    /**
     * 配置动态数据源
     * 
     * 注意：当使用ShardingSphere时（spring.profiles.active=sharding），
     * 动态数据源不会生效，由ShardingSphere直接管理数据源
     * 
     * @param oldDataSource 老表数据源
     * @param newDataSource 新表数据源
     * @return 动态数据源
     */
    @Bean(name = "dynamicDataSource")
    @Primary
    @ConditionalOnProperty(name = "sharding.enabled", havingValue = "true", matchIfMissing = false)
    public DynamicDataSource dynamicDataSource(
            @Qualifier("oldDataSource") DataSource oldDataSource,
            @Qualifier("newDataSource") DataSource newDataSource) {

        // 创建目标数据源Map
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceContextHolder.OLD_DATASOURCE, oldDataSource);
        if (newDataSource != null) {
            targetDataSources.put(DataSourceContextHolder.NEW_DATASOURCE, newDataSource);
        }

        // 创建动态数据源
        // 默认使用老表数据源
        return new DynamicDataSource(targetDataSources, oldDataSource);
    }

    /**
     * 配置SqlSessionFactory
     * 
     * @param dataSource 数据源
     * @return SqlSessionFactory
     * @throws Exception 异常
     */
    @Bean
    @Primary
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dynamicDataSource") DataSource dataSource) throws Exception {
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
    public DataSourceTransactionManager transactionManager(@Qualifier("dynamicDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }
}