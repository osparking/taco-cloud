package tacos.web;

import static java.lang.Short.valueOf;

import java.util.HashMap;
import java.util.Map;

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

  private Map<Short, Ingredient> ingredientMap = new HashMap<>();

  private void makeMap() {
    try {
      ingredientRepo.findAll().forEach(ing -> {
        ingredientMap.put(Short.valueOf(ing.getId().toString()), ing);
      });
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public Ingredient convert(String sn) {
    if (ingredientMap.size() == 0) {
      makeMap();
    }
    return ingredientMap.get(valueOf(sn));
  }
}
