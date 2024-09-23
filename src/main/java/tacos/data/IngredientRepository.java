package tacos.data;

import org.springframework.data.repository.CrudRepository;

import tacos.Ingredient;

import java.util.Optional;

public interface IngredientRepository
    extends CrudRepository<Ingredient, Long> {
  Ingredient findByName(String name);
  Optional<Ingredient> findByCode(String code);
}
