package tacos.web;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import tacos.Taco;
import tacos.TacoOrder;
import tacos.data.OrderRepository;
import tacos.data.UserRepository;
import tacos.entity.TacoUser;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
@AllArgsConstructor
public class OrderController {

  private OrderRepository orderRepository;

  @GetMapping("/current")
  public String processOrder() {
    return "orderForm";
  }

  @PostMapping
  public String processOrder(@Valid TacoOrder order, Errors errors,
                             SessionStatus sessionStatus,
                             @AuthenticationPrincipal TacoUser tacoUser) {

    if (errors.hasErrors()) {
      return "orderForm";
    }

    for (Taco taco: order.getTacos()) {
      taco.setOrder(order);
    }
    order.setTacoUser(tacoUser);
    orderRepository.save(order);
    log.info("타코 주문 : {}", order);
    sessionStatus.setComplete();

    return "redirect:/";
  }

  @GetMapping
  public String ordersForUser(
          @AuthenticationPrincipal TacoUser user, Model model) {
    var page = PageRequest.of(0, 2);
    model.addAttribute("orders",
            orderRepository.findByTacoUserOrderByPlacedAtDesc(user, page));

    return "orderList";
  }
}
