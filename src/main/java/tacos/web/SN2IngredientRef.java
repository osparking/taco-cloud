package tacos.web;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import tacos.IngredientRef;

@Component
public class SN2IngredientRef implements Converter<String, IngredientRef> {

  @Override
  public IngredientRef convert(String snStr) {
    return new IngredientRef(Short.valueOf(snStr));
  }

}
