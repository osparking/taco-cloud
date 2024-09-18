package tacos.web.api;

import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import tacos.TacoOrder;
import tacos.data.OrderRepository;
import tacos.entity.TacoUser;

@RestController
@RequestMapping(path="/api/orders", produces = "application/json")
@CrossOrigin(origins="http://tacocloud:8080")
@AllArgsConstructor
public class OrderApiController {

    private OrderRepository orderRepository;

    @DeleteMapping("/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable("orderId") Long orderId) {
        try {
            orderRepository.deleteById(orderId);
        } catch (EmptyResultDataAccessException e) {}
    }

    @GetMapping("/{id}")
    public ResponseEntity<TacoOrder> tacoOrderById(@PathVariable("id") Long id) {
        var optTacoOrder = orderRepository.findById(id);

        if (optTacoOrder.isPresent()) {
            return new ResponseEntity<>(optTacoOrder.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
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
