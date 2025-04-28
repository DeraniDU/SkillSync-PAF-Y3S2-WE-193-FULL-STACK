package com.SkillSync.user_services.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.SkillSync.user_services.repo")
public class MongoConfig {
    // The MongoTemplate bean is automatically created by Spring Boot
    // when using spring.data.mongodb.uri in application.properties
}
