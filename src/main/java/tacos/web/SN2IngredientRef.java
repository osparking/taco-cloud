package tacos.web;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import tacos.IngredientRef;

@Component
public class SN2IngredientRef implements Converter<Integer, IngredientRef> {

  @Override
  public IngredientRef convert(Integer sn) {
    return new IngredientRef(sn.shortValue());
  }

}
