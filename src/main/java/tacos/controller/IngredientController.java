package tacos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import tacos.data.IngredientRepository;

@RestController
@Slf4j
@RequestMapping
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
}
