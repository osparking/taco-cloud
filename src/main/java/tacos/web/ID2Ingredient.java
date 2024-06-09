package tacos.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import tacos.Ingredient;
import tacos.Ingredient.Type;

@Component
public class ID2Ingredient implements Converter<String, Ingredient> {
  private Map<String, Ingredient> ingredients = new HashMap<>();

  @Override
  public Ingredient convert(String id) {
    return ingredients.get(id);
  }

  public ID2Ingredient() {
    super();
    ingredients.put("FLTO",
        new Ingredient("FLTO", "밀가루 토르티야", Type.WRAP));
    ingredients.put("COTO",
        new Ingredient("COTO", "옥수수 토르티야", Type.WRAP));
    ingredients.put("GRBF",
        new Ingredient("GRBF", "다진 소고기", Type.PROTEIN));
    ingredients.put("CARN",
        new Ingredient("CARN", "삶은 돼지고기", Type.PROTEIN));
    ingredients.put("TMTO",
        new Ingredient("TMTO", "토마토 깍뚜기", Type.VEGGIES));
    ingredients.put("LETC",
        new Ingredient("LETC", "상추", Type.VEGGIES));
    ingredients.put("CHED",
        new Ingredient("CHED", "체더치즈", Type.CHEESE));
    ingredients.put("JACK",
        new Ingredient("JACK", "몬테레이 잭", Type.CHEESE));
    ingredients.put("SLSA",
        new Ingredient("SLSA", "살사", Type.SAUCE));
    ingredients.put("SRCR",
        new Ingredient("SRCR", "사워 크림", Type.SAUCE));
  }
}
