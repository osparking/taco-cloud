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

  @GetMapping("/ingredient/{sn}")
  public void logFlto(@PathVariable String sn) {
    var optIngre = repository.findById(Short.valueOf(sn));
    if (optIngre.isPresent()) {
      log.info(optIngre.toString());
    } else {
      log.error("없는 재료 일련번호: " + sn);
    }
  }
}
