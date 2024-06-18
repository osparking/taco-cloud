package tacos.web;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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

  @ModelAttribute
  public void addIngredientsToModel(Model model) {
    for (Type type : Ingredient.Type.values()) {
      model.addAttribute(type.toString().toLowerCase(),
          StreamSupport.stream(ingredientRepo.findAll().spliterator(), false)
              .filter(x -> x.getType().equals(type))
              .collect(Collectors.toList()));
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
