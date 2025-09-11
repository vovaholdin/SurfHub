package com.goldin.config;

import com.zaxxer.hikari.HikariDataSource;
import liquibase.integration.spring.SpringLiquibase;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class DataBaseConfig {

    @Bean
    public SpringLiquibase liquibase(DataSource dataSource) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(dataSource);
        liquibase.setChangeLog("classpath:db/changelog/master.yaml");
        return liquibase;
    }

    @Bean
    public DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setJdbcUrl("jdbc:postgresql://localhost:5433/mydb");
        dataSource.setUsername("admin");
        dataSource.setPassword("root");
        return dataSource;
    }

    @Bean
    LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource());
        sessionFactoryBean.setPackagesToScan("com.goldin.entity");
        Properties props = new Properties();
        props.setProperty("hibernate.hbm2ddl.auto", "none");
        props.setProperty("hibernate.show_sql", "true");
        props.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        sessionFactoryBean.setHibernateProperties(props);
        return sessionFactoryBean;
    }

    @Bean
    HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);
        return transactionManager;
    }

//    @Bean
//    LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
//        entityManagerFactoryBean.setDataSource(dataSource());
//        entityManagerFactoryBean.setPackagesToScan("com.goldin.entity.test");
//        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
//        Properties hibernateProperties = new Properties();
//        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "create");
//        hibernateProperties.setProperty("hibernate.show_sql", "true");
//        hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
//        entityManagerFactoryBean.setJpaProperties(hibernateProperties);
//        return entityManagerFactoryBean;
//    }
//
//    @Bean
//    PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
//        return new JpaTransactionManager(entityManagerFactory);
//    }
}
