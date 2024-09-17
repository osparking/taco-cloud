package tacos.web.api;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tacos.TacoOrder;
import tacos.data.OrderRepository;

@RestController
@RequestMapping(path="/api/orders", produces = "application/json")
@CrossOrigin(origins="http://tacocloud:8080")
@AllArgsConstructor
public class OrderApiController {

    private OrderRepository orderRepository;

    @GetMapping("/{id}")
    public ResponseEntity<TacoOrder> tacoOrderById(@PathVariable("id") Long id) {
        var optTacoOrder = orderRepository.findById(id);

        if (optTacoOrder.isPresent()) {
            return new ResponseEntity<>(optTacoOrder.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
