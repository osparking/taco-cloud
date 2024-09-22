package tacos.data;

import org.springframework.data.repository.CrudRepository;

import tacos.Ingredient;

import java.util.Optional;

public interface IngredientRepository
    extends CrudRepository<Ingredient, Short> {
  Ingredient findByName(String name);
  Optional<Ingredient> findByCode(String code);
}
