package tacos.web.api;

import lombok.AllArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import tacos.TacoOrder;
import tacos.data.OrderRepository;
import tacos.entity.TacoUser;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping(path="/api/orders", produces = "application/json")
@CrossOrigin(origins="http://tacocloud:8080")
@AllArgsConstructor
public class OrderApiController {

    private OrderRepository orderRepository;

    @DeleteMapping("/{orderId}")
    public ResponseEntity deleteOrder(@PathVariable("orderId") Long orderId) {
        try {
            if (orderRepository.findById(orderId).isEmpty()) {
                return new ResponseEntity(null, NOT_FOUND);
            } else {
                orderRepository.deleteById(orderId);
            }
        } catch (EmptyResultDataAccessException e) {}

        return new ResponseEntity(null, NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TacoOrder> tacoOrderById(@PathVariable("id") Long id) {
        var optTacoOrder = orderRepository.findById(id);

        if (optTacoOrder.isPresent()) {
            return new ResponseEntity<>(optTacoOrder.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, NOT_FOUND);
        }
    }

    @PatchMapping(path="/{orderId}",
            consumes="application/json", produces = "application/json")
    public TacoOrder patchOrder(@PathVariable("orderId") Long orderId,
                                @RequestBody TacoOrder patch) {

        TacoOrder order = orderRepository.findById(orderId).get();

        if (patch.getCustName() != null) {
            order.setCustName(patch.getCustName());
        }
        if (patch.getDeliZip() != null) {
            order.setDeliZip(patch.getDeliZip());
        }
        if (patch.getDeliRoadAddr() != null) {
            order.setDeliRoadAddr(patch.getDeliRoadAddr());
        }
        if (patch.getDeliDetailAddr() != null) {
            order.setDeliDetailAddr(patch.getDeliDetailAddr());
        }
        if (patch.getCcNumber() != null) {
            order.setCcNumber(patch.getCcNumber());
        }
        if (patch.getCcCvv() != null) {
            order.setCcCvv(patch.getCcCvv());
        }
        if (patch.getCcExprYM() != null) {
            order.setCcExprYM(patch.getCcExprYM());
        }
        return orderRepository.save(order);
    }

    @PutMapping(path = "/{orderId}",
            consumes = "application/json", produces = "application/json")
    public TacoOrder putOrder(
            @PathVariable("orderId") Long orderId, Authentication authentication,
            @RequestBody TacoOrder order) {
        order.setId(orderId);
        order.setTacoUser((TacoUser) authentication.getPrincipal());
        return orderRepository.save(order);
    }
}
