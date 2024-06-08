package tacos.web;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import lombok.extern.slf4j.Slf4j;
import tacos.Ingredient;
import tacos.Ingredient.Type;
import tacos.Taco;
import tacos.TacoOrder;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {
  private Iterable<Ingredient> filterByType(
      List<Ingredient> ingredients, Type type) {
    return ingredients
        .stream()
        .filter(x -> x.getType().equals(type))
        .collect(Collectors.toList());
  }

  @ModelAttribute
  public void addIngredientsToModel(Model model) {
    List<Ingredient> ingredients = Arrays.asList(
        new Ingredient("FLTO", "밀가루 토르티야", Type.WRAP),
        new Ingredient("COTO", "옥수수 토르티야", Type.WRAP),
        new Ingredient("GRBF", "다진 소고기", Type.PROTEIN),
        new Ingredient("CARN", "삶은 돼지고기", Type.PROTEIN),
        new Ingredient("TMTO", "토마토 깍뚜기", Type.VEGGIES),
        new Ingredient("LETC", "상추", Type.VEGGIES),
        new Ingredient("CHED", "체더치즈", Type.CHEESE),
        new Ingredient("JACK", "몬테레이 잭", Type.CHEESE),
        new Ingredient("SLSA", "살사", Type.SAUCE),
        new Ingredient("SRCR", "사워 크림", Type.SAUCE));

    Type[] types = Ingredient.Type.values();
    for (Type type : types) {
      model.addAttribute(type.toString().toLowerCase(),
          filterByType(ingredients, type));
    }
  }

  @ModelAttribute(name = "tacoOrder")
  public TacoOrder order() {
    return new TacoOrder();
  }

  @ModelAttribute(name = "taco")
  public Taco taco() {
    return new Taco();
  }

  @GetMapping
  public String showDesignForm() {
    return "design";
  }
}
