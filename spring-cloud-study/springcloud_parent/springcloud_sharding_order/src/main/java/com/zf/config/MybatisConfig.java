package com.zf.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * MyBatis 配置类
 * 手动配置 MyBatis 与 ShardingSphere 的集成
 * 
 * 注意：此配置类仅在未使用sharding profile时生效
 * 当使用sharding profile时，由ShardingDataSourceConfig接管
 */
@Configuration
@Conditional(NotShardingProfileCondition.class)
@MapperScan(basePackages = "com.zf.mapper", sqlSessionTemplateRef = "sqlSessionTemplate")
public class MybatisConfig {

    /**
     * 创建 SqlSessionFactory
     * 使用自动配置的数据源
     */
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setTypeAliasesPackage("com.zf.entity");
        bean.setMapperLocations(
            new PathMatchingResourcePatternResolver()
                .getResources("classpath:mapper/*.xml")
        );
        return bean.getObject();
    }

    /**
     * 创建 SqlSessionTemplate
     */
    @Bean(name = "sqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}