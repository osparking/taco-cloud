package tacos.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import tacos.Ingredient;
import tacos.Ingredient.Type;

@Repository
public class JdbcIngredientRepository implements IngredientRepository {

  private JdbcTemplate jdbcTemplate;
  private SimpleJdbcInsert insertIngredient;

  public JdbcIngredientRepository(JdbcTemplate jdbcTemplate) {
    super();
    this.jdbcTemplate = jdbcTemplate;
    insertIngredient = new SimpleJdbcInsert(jdbcTemplate)
        .withTableName("ingredient").usingGeneratedKeyColumns("sn");
  }

  private Ingredient mapRowToIngredient(ResultSet row, int rowNum)
      throws SQLException {
    return new Ingredient(
        row.getShort("sn"),
        row.getString("id"),
        row.getString("name"),
        Type.values()[row.getInt("type")]);
  }

  @Override
  public List<Ingredient> findAll() {
    return jdbcTemplate.query(
        "select * from Ingredient",
        this::mapRowToIngredient);
  }

  @Override
  public Optional<Ingredient> findById(String id) {
    List<Ingredient> results = jdbcTemplate.query(
        "select * from Ingredient where id=?",
        this::mapRowToIngredient,
        id);
    return results.size() == 0 ? Optional.empty() : Optional.of(results.get(0));
  }

  @Override
  public Number save(Ingredient ingredient) {
    final Map<String, Object> parameters = new HashMap<>();
    parameters.put("id", ingredient.getId().toUpperCase());
    parameters.put("name", ingredient.getName());
    parameters.put("type", ingredient.getType().ordinal());

    return insertIngredient.executeAndReturnKey(parameters);
  }
}
