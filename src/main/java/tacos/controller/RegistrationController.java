package tacos.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tacos.data.RegistrationForm;
import tacos.data.UserRepository;
import tacos.entity.TacoUser;
import org.springframework.ui.Model;

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
  public String registerForm(HttpServletRequest request) {
    request.getSession().invalidate();
    return "registration";
  }

  @PostMapping
  public String processRegistration(RegistrationForm form) {
    if (userRepo.findByUsername(form.getUsername()) == null) {
      userRepo.save(form.toUser(passwordEncoder));
      return "redirect:/login";
    } else {
      return "redirect:/register?uname=inUse";
    }
  }

}