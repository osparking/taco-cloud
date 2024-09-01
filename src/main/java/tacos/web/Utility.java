package tacos.web;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;

import tacos.entity.TacoUser;

public class Utility {
  
  public static void putUsernameTo(Model model) {
    
    /**
     * 유저 로그인 정보(username) 모델에 엊는다.
     */
    var auth = SecurityContextHolder.getContext().getAuthentication();    
    String username = null;

    if (!(auth instanceof AnonymousAuthenticationToken)) {
      username = ((TacoUser)auth.getPrincipal()).getUsername();
    }
    model.addAttribute("username", username);    
  }
}
