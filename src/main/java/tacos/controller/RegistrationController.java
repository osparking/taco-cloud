package tacos.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import tacos.data.RegistrationForm;
import tacos.data.UserRepository;
import tacos.entity.TacoUser;

@Controller
@RequestMapping("/register")
public class RegistrationController {

  private UserRepository userRepo;
  private PasswordEncoder passwordEncoder;

  public RegistrationController(
      UserRepository userRepo, PasswordEncoder passwordEncoder) {
    this.userRepo = userRepo;
    this.passwordEncoder = passwordEncoder;
  }

  @ModelAttribute(name = "tacoUser")
  public TacoUser user() {
    var user = new TacoUser("user", "1234", "홍길동", "12345", 
        "경기도 하남시 미사강변중앙로 120", "102동 1105호", "010-1234-5678");
    return user;
  }
  
  @GetMapping
  public String registerForm() {
    return "registration";
  }

  @PostMapping
  public String processRegistration(RegistrationForm form) {
    userRepo.save(form.toUser(passwordEncoder));
    return "redirect:/login";
  }

}