package tacos.data;

import java.util.List;

import tacos.TacoOrder;

public interface OrderRepository {
  TacoOrder save(TacoOrder order);

  List<TacoOrder> findByDeliZip(String deliZip);
}
