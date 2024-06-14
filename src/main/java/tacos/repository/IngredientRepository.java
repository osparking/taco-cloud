package tacos.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;
import tacos.Ingredient;
import tacos.Ingredient.Type;

@Slf4j
@Repository
public class IngredientRepository {

  @Autowired
  DataSource dataSource;

  public Optional<Ingredient> findById(String id) {
    Connection connection = null;
    PreparedStatement statement = null;
    ResultSet resultSet = null;

    try {
      connection = dataSource.getConnection();
      statement = connection
          .prepareStatement("select id, name, type from Ingredient where id=?");
      statement.setString(1, id);
      resultSet = statement.executeQuery();
      Ingredient ingredient = null;
      if (resultSet.next()) {
        ingredient = new Ingredient(resultSet.getString("id"),
            resultSet.getString("name"),
            Type.values()[resultSet.getInt("type")]);
      }
      return Optional.of(ingredient);
    } catch (SQLException e) {
      log.error(e.getMessage());
    } finally {
      if (resultSet != null) {
        try {
          resultSet.close();
        } catch (SQLException e) {
        }
      }
      if (statement != null) {
        try {
          statement.close();
        } catch (SQLException e) {
        }
      }
      if (connection != null) {
        try {
          connection.close();
        } catch (SQLException e) {
        }
      }
    }
    return Optional.empty();
  }
}
