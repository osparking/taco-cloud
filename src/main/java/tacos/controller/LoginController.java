package tacos.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tacos.web.Utility;

@Controller
@RequestMapping("/login")
public class LoginController {
  @GetMapping
  public String loginForm(Model model,
      @RequestParam(required = false) String logout) {
    String logoutMsg = null;

    if (logout != null && "true".equals(logout)) {
      SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
      logoutMsg = "성공적으로 로그아웃 되었습니다.";
    }
    model.addAttribute("logoutMsg", logoutMsg);
    Utility.putUsernameTo(model);
   
    return "login";
  }
}
