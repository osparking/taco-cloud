package tacos.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import tacos.Ingredient;
import tacos.Ingredient.Type;
import tacos.Taco;
import tacos.TacoOrder;
import tacos.data.IngredientRepository;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {
  private final IngredientRepository ingredientRepo;

  public DesignTacoController(
      IngredientRepository ingredientRepo) {
    this.ingredientRepo = ingredientRepo;
  }

  @PostMapping
  public String processTaco(@Valid Taco taco, Errors errors,
      @ModelAttribute TacoOrder tacoOrder) {

    if (errors.hasErrors()) {
      return "design";
    }

    tacoOrder.addTaco(taco);
    log.info("주문 처리 대상: {}", taco);

    return "redirect:/orders/current";
  }

  private Iterable<Ingredient> ingredientByType(
      Iterable<Ingredient> ingredients, Type type) {
    var ings = ingredients.iterator();
    List<Ingredient> ingOfType = new ArrayList<>();
    while (ings.hasNext()) {
      var x = ings.next();
      if (x.getType().equals(type)) {
        ingOfType.add(x);
      }
    }
    return ingOfType;
  }

  @ModelAttribute
  public void addIngredientsToModel(Model model) {
    Iterable<Ingredient> ingredients = ingredientRepo.findAll();

    Type[] types = Ingredient.Type.values();
    for (Type type : types) {
      model.addAttribute(type.toString().toLowerCase(),
          ingredientByType(ingredients, type));
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
