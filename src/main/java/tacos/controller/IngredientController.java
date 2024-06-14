package tacos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import tacos.repository.IngredientRepository;

@RestController
@Slf4j
@RequestMapping
public class IngredientController {

  @Autowired
  IngredientRepository repository;

  @GetMapping("/flto")
  public void logFlto() {
    var optIngre = repository.findById("FLTO");
    if (optIngre.isPresent()) {
      log.info(optIngre.toString());
    } else {
      log.error("FLTO 재료가 없습니다");
    }
  }
}
