package tacos.web;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import tacos.Ingredient;
import tacos.data.IngredientRepository;

@Component
public class ID2Ingredient implements Converter<String, Ingredient> {
  private final IngredientRepository ingredientRepo;

  public ID2Ingredient(IngredientRepository repository) {
    ingredientRepo = repository;
  }

  @Override
  public Ingredient convert(String sn) {
    return ingredientRepo.findById(Short.valueOf(sn)).orElse(null);
  }
}
