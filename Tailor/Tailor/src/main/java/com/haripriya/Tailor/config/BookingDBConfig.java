//package com.haripriya.Tailor.config;
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
//        basePackages = "com.haripriya.Tailor.Repository.booking",
//        entityManagerFactoryRef = "bookingEntityManager",
//        transactionManagerRef = "bookingTransactionManager"
//)
//public class BookingDBConfig {
//
//    @Bean
//    @ConfigurationProperties("spring.datasource.booking")
//    public DataSource bookingDataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean bookingEntityManager(
//            EntityManagerFactoryBuilder builder) {
//        return builder
//                .dataSource(bookingDataSource())
//                .packages("com.haripriya.Tailor.model")
//                .persistenceUnit("booking")
//                .build();
//    }
//
//    @Bean
//    public PlatformTransactionManager bookingTransactionManager(
//            @Qualifier("bookingEntityManager") EntityManagerFactory entityManagerFactory) {
//        return new JpaTransactionManager(entityManagerFactory);
//    }
//
//}
