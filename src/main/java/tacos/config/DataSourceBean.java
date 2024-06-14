package tacos.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceBean {
  @Value("${spring.datasource.url}")
  String dbUrl;

  @Value("${spring.datasource.driver-class-name}")
  String driverClass;

  @Value("${spring.datasource.username}")
  String username;

  @Value("${spring.datasource.password}")
  String password;

  @Bean
  DataSource dataSource() {
    return DataSourceBuilder.create().driverClassName(driverClass)
        .url(dbUrl).username(username).password(password).build();
  }
}
