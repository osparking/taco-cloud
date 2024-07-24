package tacos.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Formatter;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecuriConfi {

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  UserDetailsService userDetailsService(PasswordEncoder encoder) {
    List<UserDetails> usersList = new ArrayList<>();
    usersList.add(new User(
        "kim", encoder.encode("1234"),
        Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))));
    usersList.add(new User(
        "lee", encoder.encode("1234"),
        Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"))));
    return new InMemoryUserDetailsManager(usersList);
  }

  @Bean
  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
      .authorizeHttpRequests(authz ->
        authz
          .requestMatchers("/design", "/orders").hasRole("USER")
          .requestMatchers("/", "/**").permitAll());
    return http.build();
  }
}
