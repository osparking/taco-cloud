package tacos.data;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import tacos.TacoOrder;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {
  List<TacoOrder> findByDeliZip(String deliZip);

  List<TacoOrder> readOrdersByDeliZipAndPlacedAtBetween(
      String deliveryZip, LocalDate startDate, LocalDate endDate);
}
