package tacos.web;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import tacos.IngredientRef;

@Component
public class ID2IngredientRef implements Converter<String, IngredientRef> {

  @Override
  public IngredientRef convert(String idStr) {
    return new IngredientRef(Long.valueOf(idStr));
  }
}