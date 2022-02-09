package perscholas.ecommercecasestudy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "perscholas.ecommercecasestudy.database")
public class DatabaseConfig {

}