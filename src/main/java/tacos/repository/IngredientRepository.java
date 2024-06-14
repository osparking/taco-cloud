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

    String findIngID = "select id, name, type from Ingredient where id=?";

    try (Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection
            .prepareStatement(findIngID);) {

      statement.setString(1, id.toUpperCase());
      try (ResultSet resultSet = statement.executeQuery()) {
        if (resultSet.next()) {
          return Optional.of(new Ingredient(resultSet.getString("id"),
              resultSet.getString("name"),
              Type.values()[resultSet.getInt("type")]));
        }
      } catch (Exception e) {
        log.error(e.getMessage());
      }
    } catch (SQLException e) {
      log.error(e.getMessage());
    }
    return Optional.empty();
  }
}
