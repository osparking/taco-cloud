package tacos.data;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.Repository;

import tacos.Ingredient;

public interface IngredientRepository extends Repository<Ingredient, Short> {
  List<Ingredient> findAll();

  Optional<Ingredient> findById(String id);

  Ingredient save(Ingredient ingredient);
}
