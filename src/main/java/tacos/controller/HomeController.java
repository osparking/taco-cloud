package tacos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import tacos.web.Utility;

@Controller
public class HomeController {
  @GetMapping("/")
  public String homePage(Model model) {
    Utility.putUsernameTo(model);
    return "home";
  }
}
