package tacos.web;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.annotation.PostConstruct;
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

  private SN2IngredientRef sn2IngredientRef;

  @Autowired
  private ApplicationContext applicationContext;

  @PostConstruct
  public void init() {
    try {
      sn2IngredientRef = applicationContext.getBean(SN2IngredientRef.class);
      log.info("변환기 존재");
    } catch (NoSuchBeanDefinitionException e) {
      log.info("변환기 없음");
    }
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
              .filter(x -> x.getTypeOrd().equals((short) type.ordinal()))
              .collect(Collectors.toList()));
    }
  }

  @ModelAttribute(name = "tacoOrder")
  public TacoOrder order() {
    var order = new TacoOrder();

    order.setCustName("김만복");
    order.setDeliZip("15230");
    order.setDeliRoadAddr("경기도 군포시 당정1로 27");
    order.setDeliDetailAddr("1001동 101호");
    order.setCcNumber("4111111111111111");
    order.setCcExprY_M("12/26");
    order.setCcCvv("123");

    return order;
  }

  @ModelAttribute(name = "taco")
  public Taco taco() {
    var taco = new Taco();
    taco.setName("최애 타코");
    return taco;
  }

  @GetMapping
  public String showDesignForm() {
    return "design";
  }
}
