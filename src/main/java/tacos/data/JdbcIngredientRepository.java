package tacos.data;

import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import tacos.Ingredient;

@Repository
public class JdbcIngredientRepository implements IngredientRepository {

  private JdbcTemplate jdbcTemplate;

  @Override
  public Iterable<Ingredient> findAll() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Optional<Ingredient> findById(String id) {
    // TODO Auto-generated method stub
    return Optional.empty();
  }

  @Override
  public Ingredient save(Ingredient ingredient) {
    // TODO Auto-generated method stub
    return null;
  }

}
