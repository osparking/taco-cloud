package tacos.web;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;

public class Utility {
  
  public static void putUsernameTo(Model model) {
    
    /**
     * 유저 로그인 정보(username) 모델에 엊는다.
     */
    var auth = SecurityContextHolder.getContext().getAuthentication();
    String username = null;
    String socialType = null;

    if (!(auth instanceof AnonymousAuthenticationToken)) {
//      if (auth.getPrincipal() instanceof OAuth2User) {
//        var token = (OAuth2AuthenticationToken) auth;
//        var attributes = ((OAuth2User) auth.getPrincipal()).getAttributes();
//
//        socialType = token.getAuthorizedClientRegistrationId();
//        switch (socialType) {
//          case "naver":
//            attributes = token.getPrincipal().getAttribute("response");
//            break;
//          case "kakao":
//            attributes = (token.getPrincipal().getAttribute("kakao_account"));
//            break;
//          default:
//            break;
//        }
//        username = attributes.get("email").toString();
//      } else {
//        username = ((TacoUser) auth.getPrincipal()).getUsername();
//      }
    }
    model.addAttribute("socialType", socialType);
    model.addAttribute("username", username);
  }
}
