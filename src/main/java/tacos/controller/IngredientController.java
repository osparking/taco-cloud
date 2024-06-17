package tacos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import tacos.Ingredient;
import tacos.data.IngredientRepository;

@Slf4j
@RequestMapping
@Controller
public class IngredientController {

  @Autowired
  IngredientRepository repository;

  @GetMapping("/ingredient/{id}")
  public void logFlto(@PathVariable String id) {
    var idUpper = id.toUpperCase();
    var optIngre = repository.findById(idUpper);
    if (optIngre.isPresent()) {
      log.info(optIngre.toString());
    } else {
      log.error(idUpper + ": 재료가 없습니다");
    }
  }

  @ModelAttribute(name = "ingredient")
  public Ingredient ingredient() {
    return new Ingredient();
  }

  @GetMapping("/ingredient/new")
  public String newIngredientForm() {
    return "ingredientForm";
  }

  @PostMapping("/ingredient/new")
  public String saveIngredient(Ingredient ingredient) {
    Number newSN = repository.save(ingredient);
    log.info("추가된 재료 일련번호: {}", newSN);

    return "redirect:/";
  }
}
