package tacos.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import tacos.Ingredient;
import tacos.data.IngredientRepository;

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

  @Bean
  ApplicationRunner ingredientLoader(IngredientRepository repo) {
    return (args) -> {
      repo.save(new Ingredient("FLTO", "밀가루 토르티야", (short) 0));
      repo.save(new Ingredient("COTO", "옥수수 토르티야", (short) 0));
      repo.save(new Ingredient("GRBF", "다진 소고기", (short) 1));
      repo.save(new Ingredient("CARN", "삶은 돼지고기", (short) 1));
      repo.save(new Ingredient("TMTO", "토마토 깍뚜기", (short) 2));
      repo.save(new Ingredient("LETC", "상추", (short) 2));
      repo.save(new Ingredient("CHED", "체더치즈", (short) 3));
      repo.save(new Ingredient("JACK", "몬테레이 잭", (short) 3));
      repo.save(new Ingredient("SLSA", "살사", (short) 4));
      repo.save(new Ingredient("SRCR", "사워 크림", (short) 4));
    };
  }
}
