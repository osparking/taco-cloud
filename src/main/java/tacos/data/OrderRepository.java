package tacos.data;

import java.time.LocalDate;
import java.util.List;

import tacos.TacoOrder;

public interface OrderRepository {
  TacoOrder save(TacoOrder order);

  List<TacoOrder> findByDeliZip(String deliZip);

  List<TacoOrder> readOrdersByDeliZipPlacedAtBetween(String deliZip,
      LocalDate dateStart, LocalDate dateEnd);
}
