//package com.haripriya.Tailor.config;
//
//import jakarta.persistence.EntityManagerFactory;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.sql.DataSource;
//
//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(
//        basePackages = "com.haripriya.Tailor.Repository.total",
//        entityManagerFactoryRef = "userEntityManager",
//        transactionManagerRef = "userTransactionManager"
//)
//public class UserDBConfig {
//
//    @Primary
//    @Bean
//    @ConfigurationProperties("spring.datasource.user")
//    public DataSource userDataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Primary
//    @Bean
//    public LocalContainerEntityManagerFactoryBean userEntityManager(
//            EntityManagerFactoryBuilder builder) {
//        return builder
//                .dataSource(userDataSource())
//                .packages("com.haripriya.Tailor.model")
//                .persistenceUnit("user")
//                .build();
//    }
//
//    @Primary
//    @Bean
//    public PlatformTransactionManager userTransactionManager(
//            @Qualifier("userEntityManager") EntityManagerFactory entityManagerFactory) {
//        return new JpaTransactionManager(entityManagerFactory);
//    }
//}
