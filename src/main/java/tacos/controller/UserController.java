package tacos.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tacos.data.UserRepository;
import tacos.entity.TacoUser;

import java.util.Optional;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;
    @GetMapping("/user")
    public Optional<TacoUser> getUserById(@RequestParam Long id) {
        return userRepository.findById(id);
    }
}
