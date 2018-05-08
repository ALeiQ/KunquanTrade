/*
 * Copyright (C) 2018 Baidu, Inc. All Rights Reserved.
 */
package com.sdut.trade.conf;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import javax.sql.DataSource;

/**
 * 类描述：数据库配置
 *
 * @author liuzixiang[422206217@qq.com]
 * @date 2018/3/9
 */
@Configuration
@MapperScan(basePackages = "com.sdut.trade.mapper",sqlSessionTemplateRef = "tradeSqlSessionTemplate")
public class TradeDataSourceConfig {

    @Bean(name = "tradeDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.trade")
    @Primary
    public DataSource tradeDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "tradeSqlSessionFactory")
    @Primary
    public SqlSessionFactory tradeSqlSessionFactory(
            @Qualifier("tradeDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);

        //ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        //try {
        //    bean.setMapperLocations(resolver.getResources("classpath*:/mapper/*Mapper.xml"));
        //    return bean.getObject();
        //} catch (Exception e) {
        //    e.printStackTrace();
        //    throw new RuntimeException(e);
        //}

        return bean.getObject();
    }

    @Bean(name = "tradeTransactionManager")
    @Primary
    public DataSourceTransactionManager tradeTransactionManager(
            @Qualifier("tradeDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "tradeSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate tradeSqlSessionTemplate(
            @Qualifier("tradeSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
