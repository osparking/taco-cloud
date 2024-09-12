package tacos.data;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import tacos.TacoOrder;
import tacos.entity.TacoUser;

public interface OrderRepository extends CrudRepository<TacoOrder, Long> {
  List<TacoOrder> findByDeliZip(String deliZip);

  List<TacoOrder> readOrdersByDeliZipAndPlacedAtBetween(
      String deliveryZip, LocalDate startDate, LocalDate endDate);

    List<TacoOrder> findByTacoUserOrderByPlacedAtDesc(TacoUser user, Pageable pageable);
}

