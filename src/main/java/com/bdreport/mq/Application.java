package com.bdreport.mq;

import javax.jms.Queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@ComponentScan(basePackages = "com.bdreport")
@PropertySource(value= "classpath:/properties/local/application.properties")
@EnableJms
public class Application {

    @Configuration
    @Profile("production")
    @PropertySource("classpath:/properties/production/application.properties")
    static class Production
    { }

    @Configuration
    @Profile("local")
    @PropertySource({"classpath:/properties/local/application.properties"})
    static class Local
    { }

    public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
