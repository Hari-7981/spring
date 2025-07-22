//package com.haripriya.Tailor.config;
//
//
//
//import jakarta.persistence.EntityManagerFactory;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
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
//        basePackages = "com.haripriya.Tailor.Repository.ChatMessage",
//        entityManagerFactoryRef = "chatEntityManager",
//        transactionManagerRef = "chatTransactionManager"
//)
//public class ChatDBConfig {
//
//    @Bean
//    @ConfigurationProperties("spring.datasource.chat")
//    public DataSource chatDataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean chatEntityManager(
//            EntityManagerFactoryBuilder builder) {
//        return builder
//                .dataSource(chatDataSource())
//                .packages("com.haripriya.Tailor.model")
//                .persistenceUnit("chat")
//                .build();
//    }
//
//    @Bean
//    public PlatformTransactionManager chatTransactionManager(
//            @Qualifier("chatEntityManager") EntityManagerFactory entityManagerFactory) {
//        return new JpaTransactionManager(entityManagerFactory);
//    }
//}
