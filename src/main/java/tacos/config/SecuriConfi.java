package tacos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecuriConfi {

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
      .authorizeHttpRequests(authz ->
        authz
          .requestMatchers("/design", "/orders/**").authenticated()
          .requestMatchers("/", "/**").permitAll());

    http.formLogin(foLoCfgr -> foLoCfgr.loginPage("/login")
        .defaultSuccessUrl("/design", true));
    
    http.oauth2Login(ht -> ht.loginPage("/login")
        .defaultSuccessUrl("/design", true));
    
    return http.build();
  }
  
  @Bean
  WebSecurityCustomizer ignoringCustomizer() {
    return (web) -> web.ignoring().requestMatchers("/h2-console/**");
  }
}
