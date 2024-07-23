package tacos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import tacos.data.UserRepository;
import tacos.entity.User;

@Configuration
public class UserConfig {
  @Bean
  UserDetailsService userDetailsService(UserRepository userRepo) {
    return username -> {
      User user = userRepo.findByUsername(username);
      if (user == null)
        throw new UsernameNotFoundException("유저 '" + username + "'는 없습니다.");
      else
        return user;
    };
  }
}
