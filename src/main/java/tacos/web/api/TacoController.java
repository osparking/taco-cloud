package tacos.web.api;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tacos.data.TacoRepository;

@RestController
@RequestMapping(path="/api/tacos", produces = "application/json")
@CrossOrigin(origins="http://tacocloud:8080")
@AllArgsConstructor
public class TacoController {
    private TacoRepository tacoRepository;
}
