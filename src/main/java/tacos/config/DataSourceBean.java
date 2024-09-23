package tacos.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.context.annotation.Profile;
import tacos.Ingredient;
import tacos.Taco;
import tacos.data.IngredientRepository;
import tacos.data.TacoCrudRepo;
import tacos.data.TacoRepository;

import java.util.Arrays;

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
  @Profile("dev")
  ApplicationRunner ingredientLoader(IngredientRepository repo,
                                     TacoCrudRepo tacoCrudRepo) {
    return (args) -> {
      Ingredient ing1 = new Ingredient("FLTO", "밀가루 토르티야", (short) 0);
      Ingredient ing2 = new Ingredient("COTO", "옥수수 토르티야", (short) 0);
      Ingredient ing3 = new Ingredient("GRBF", "다진 소고기", (short) 1);
      Ingredient ing4 = new Ingredient("CARN", "삶은 돼지고기", (short) 1);
      Ingredient ing5 = new Ingredient("TMTO", "토마토 깍뚜기", (short) 2);
      Ingredient ing6 = new Ingredient("LETC", "상추", (short) 2);
      Ingredient ing7 = new Ingredient("CHED", "체더치즈", (short) 3);
      Ingredient ing8 = new Ingredient("JACK", "몬테레이 잭", (short) 3);
      Ingredient ing9 = new Ingredient("SLSA", "살사", (short) 4);
      Ingredient ing10 = new Ingredient("SRCR", "사워 크림", (short) 4);

      repo.save(ing1);
      repo.save(ing2);
      repo.save(ing3);
      repo.save(ing4);
      repo.save(ing5);
      repo.save(ing6);
      repo.save(ing7);
      repo.save(ing8);
      repo.save(ing9);
      repo.save(ing10);

      Taco taco1 = new Taco();
      taco1.setCheckedWrapId(ing1.getId());
      taco1.setName("고기애호가");
      taco1.setIncludedIngredients(Arrays.asList(
              ing1, ing3, ing10, ing9, ing7));
      tacoCrudRepo.save(taco1);

      Taco taco2 = new Taco();
      taco2.setCheckedWrapId(ing2.getId());
      taco2.setName("단것이 좋아");
      taco2.setIncludedIngredients(Arrays.asList(
              ing2, ing7, ing8, ing10));
      tacoCrudRepo.save(taco2);

      Taco taco3 = new Taco();
      taco3.setCheckedWrapId(ing2.getId());
      taco3.setName("채식주의자");
      taco3.setIncludedIngredients(Arrays.asList(
              ing2, ing5, ing6, ing9));
      tacoCrudRepo.save(taco3);
    };
  }
}
