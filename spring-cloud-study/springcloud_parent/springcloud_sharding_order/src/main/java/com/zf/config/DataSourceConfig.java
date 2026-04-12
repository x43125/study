package com.zf.config;

import com.zf.datasource.DataSourceContextHolder;
import com.zf.datasource.DynamicDataSource;
import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
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
@MapperScan("com.zf.mapper")
public class DataSourceConfig {

    /**
     * 配置老表数据源
     * 
     * @return 老表数据源
     */
    @Bean(name = "oldDataSource")
    public DataSource oldDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/order_db?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("12345678");
        dataSource.setInitialSize(5);
        dataSource.setMinIdle(5);
        dataSource.setMaxActive(20);
        dataSource.setMaxWait(60000);
        return dataSource;
    }

    /**
     * 配置新表数据源（ShardingSphere）
     * 
     * 注意：当使用sharding profile时，ShardingSphere会自动创建一个名为dataSource的Bean
     * 这里我们通过@ConditionalOnProperty确保只在sharding profile下创建
     * 
     * @return ShardingSphere数据源
     */
    @Bean(name = "newDataSource")
    @ConditionalOnProperty(name = "spring.profiles.active", havingValue = "sharding")
    public DataSource newDataSource(DataSource shardingDataSource) {
        // ShardingSphere自动配置会创建名为dataSource的Bean
        // 我们直接返回它作为newDataSource
        return shardingDataSource;
    }

    /**
     * 配置动态数据源
     * 
     * 功能：
     * 1. 包装老表数据源和新表数据源
     * 2. 根据ThreadLocal中的key动态切换数据源
     * 3. 支持双写模式
     * 
     * 注意：
     * - newDataSource由ShardingSphere自动配置（当spring.profiles.active=sharding时）
     * - 如果没有启用ShardingSphere，只使用oldDataSource
     * 
     * @param oldDataSource 老表数据源
     * @return 动态数据源
     */
    @Bean(name = "dynamicDataSource")
    @Primary
    public DynamicDataSource dynamicDataSource(
            @Qualifier("oldDataSource") DataSource oldDataSource,
            org.springframework.beans.factory.ObjectProvider<DataSource> newDataSourceProvider) {

        // 创建目标数据源Map
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceContextHolder.OLD_DATASOURCE, oldDataSource);
        
        // 如果存在newDataSource（ShardingSphere），添加到目标数据源
        DataSource newDataSource = newDataSourceProvider.getIfAvailable();
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