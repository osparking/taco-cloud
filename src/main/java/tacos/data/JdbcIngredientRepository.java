package tacos.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import tacos.Ingredient;
import tacos.Ingredient.Type;

@Repository
public class JdbcIngredientRepository implements IngredientRepository {

  private JdbcTemplate jdbcTemplate;

  public JdbcIngredientRepository(JdbcTemplate jdbcTemplate) {
    super();
    this.jdbcTemplate = jdbcTemplate;
  }

  private Ingredient mapRowToIngredient(ResultSet row, int rowNum)
      throws SQLException {
    return new Ingredient(
        row.getString("id"),
        row.getString("name"),
        Type.values()[row.getInt("type")]);
  }

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
