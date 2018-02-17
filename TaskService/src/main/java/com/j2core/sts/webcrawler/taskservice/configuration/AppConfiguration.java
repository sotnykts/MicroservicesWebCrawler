package com.j2core.sts.webcrawler.taskservice.configuration;

import com.j2core.sts.webcrawler.taskservice.controller.AuthenticationController;
import com.j2core.sts.webcrawler.taskservice.controller.NodeController;
import com.j2core.sts.webcrawler.taskservice.controller.TaskController;
import com.j2core.sts.webcrawler.taskservice.dao.*;
import com.j2core.sts.webcrawler.taskservice.service.*;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Properties;
/*
 * Created by Sotnyk Tetiana.
 */

/**
 * The application configuration class with beans
 */
@Configuration
@EnableJpaRepositories("com.j2core.sts.webcrawler.taskservice")
public class AppConfiguration {

    private final static Logger LOGGER = Logger.getLogger(AppConfiguration.class);


    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.put("hibernate.show_sql", "false");
        properties.put("hibernate.hbm2ddl.auto", "update");

        return properties;
    }

    @Bean
    @Scope("singleton")
    public javax.sql.DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/testCrawlerDB");
        dataSource.setUsername("sts");
        dataSource.setPassword("StsStsSts!2#");

        return dataSource;
    }

    @Bean
    @Scope("singleton")
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(new String[] { "com.j2core.sts.webcrawler.taskservice" });
        sessionFactory.setHibernateProperties(hibernateProperties());

        return sessionFactory;
    }

    @Bean
    @Scope("singleton")
    EntityManagerFactory entityManagerFactory(){

        LOGGER.info(" create entity manager factory ");
        return Persistence.createEntityManagerFactory("crawler");

    }

    @Bean
    @Scope("session")
    public AuthenticationController authenticationController(){

        return new AuthenticationController();

    }

    @Bean
    @Scope("prototype")
    public NodeDataDao nodeDataDao(){

        return new DefaultNodeDataDao(entityManagerFactory());
    }

    @Bean
    @Scope("prototype")
    public NodeService nodeService(){

        return new DefaultNodeService(nodeDataDao());
    }

    @Bean
    @Scope("session")
    public NodeController nodeController(){

        return new NodeController(nodeService(), authenticationService());
    }

    @Bean
    @Scope("prototype")
    public PermissionDao permissionDao(){

        return new DefaultPermissionDao(entityManagerFactory());
    }

    @Bean
    @Scope("prototype")
    public RolesGroupDao rolesGroupDao(){

        return new DefaultRolesGroupDao(entityManagerFactory());
    }

    @Bean
    @Scope("prototype")
    public UserDataDao userDataDao(){

        return new DefaultUserDataDao(entityManagerFactory());
    }

    @Bean
    @Scope("prototype")
    public AuthenticationService authenticationService(){

        return new DefaultAuthenticationService(userDataDao(), permissionDao());
    }


    @Bean
    @Scope("prototype")
    public PageInformationDao pageInformationDao(){

        return new DefaultPageInformationDao(entityManagerFactory());
    }

    @Bean
    @Scope("prototype")
    public UrlDataDao urlDataDao(){

        return new DefaultUrlDataDao(entityManagerFactory());
    }

    @Bean
    @Scope("prototype")
    public WordInformationDao wordInformationDao(){

        return new DefaultWordInformationDao(entityManagerFactory());
    }

    @Bean
    @Scope("prototype")
    public TaskService taskService(){

        return new DefaultTaskService(urlDataDao(), pageInformationDao(), wordInformationDao());
    }

    @Bean
    @Scope("session")
    public TaskController taskController(){

        return new TaskController(taskService(), authenticationService());
    }

}
