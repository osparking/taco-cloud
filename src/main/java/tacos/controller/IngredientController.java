package tacos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;
import tacos.Ingredient;
import tacos.data.IngredientRepository;

@RestController
@RequestMapping(path="/api/ingredients", produces="application/json")
@CrossOrigin(origins="http://localhost:8080")
public class IngredientController {

  private IngredientRepository repository;

  @Autowired
  public IngredientController(IngredientRepository repository) {
    this.repository = repository;
  }

  @GetMapping
  public Iterable<Ingredient> allIngredients() {
    return repository.findAll();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Ingredient saveIngredient(@RequestBody Ingredient ingredient) {
    return repository.save(ingredient);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteIngredient(@PathVariable("id") Long ingredientId) {
    repository.deleteById(ingredientId);
  }

  @GetMapping("/code")
  public Ingredient getIngredientByCode(@RequestParam String code) {
    var optIngre = repository.findByCode(code);
    if (optIngre.isPresent()) {
      return optIngre.get();
    } else {
      return null;
    }
  }
}
