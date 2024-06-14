package tacos.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import tacos.Ingredient;
import tacos.Ingredient.Type;

@Repository
public class IngredientRepository {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  public Optional<Ingredient> findById(String id) {
    List<Ingredient> results = jdbcTemplate.query(
        "select id, name, type from Ingredient where id=?",
        this::mapRowToIngredient,
        id);
    return results.size() == 0 ? Optional.empty() : Optional.of(results.get(0));
  }

  private Ingredient mapRowToIngredient(ResultSet row, int rowNum)
      throws SQLException {
    return new Ingredient(
        row.getString("id"),
        row.getString("name"),
        Type.values()[row.getInt("type")]);
  }
}
