package tacos.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.apache.catalina.authenticator.SavedRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.savedrequest.DefaultSavedRequest;
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
  public String loginForm(HttpServletRequest request, Model model,
                          @RequestParam(required = false) String logout) {
    String logoutMsg = null;

    HttpSession session = request.getSession();
    var savedRequest = session.getAttribute("SPRING_SECURITY_SAVED_REQUEST");
    if (savedRequest != null) {
      var savedURL = ((DefaultSavedRequest) savedRequest).getRequestURL();
      if (savedURL.equals("http://localhost:8080/design")) {
        model.addAttribute("loginMsg", "타코 설계하려면, 로그인하세요 :-)");
      } else {
        model.addAttribute("loginMsg", null);
      }
    }
    if (logout != null && "true".equals(logout)) {
      SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
      logoutMsg = "성공적으로 로그아웃 되었습니다.";
    }
    model.addAttribute("logoutMsg", logoutMsg);
    Utility.putUsernameTo(model);
   
    return "login";
  }
}
