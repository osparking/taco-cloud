package tacos.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tacos.entity.TacoUser;

@Controller
@RequestMapping("/login")
public class LoginController {
  @GetMapping
  public String loginForm(Model model,
      @RequestParam(required = false) String logout) {
    String logoutMsg = null;
    var auth = SecurityContextHolder.getContext().getAuthentication();

    if (logout != null && "true".equals(logout)) {
      auth.setAuthenticated(false);
      logoutMsg = "성공적으로 로그아웃 되었습니다.";
    }
    model.addAttribute("logoutMsg", logoutMsg);
    
    /**
     * 유저 로그인 정보(username) 뷰단 전송
     */
    String username = null;

    if (!(auth instanceof AnonymousAuthenticationToken)) {
      username = ((TacoUser)auth.getPrincipal()).getUsername();
    }
    model.addAttribute("username", username);
    
    return "login";
  }
}


