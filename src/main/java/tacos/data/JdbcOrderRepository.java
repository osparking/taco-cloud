package tacos.data;

import java.sql.Types;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.asm.Type;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import tacos.Ingredient;
import tacos.Taco;
import tacos.TacoOrder;

public class JdbcOrderRepository implements OrderRepository {

  private JdbcOperations jdbcOperations;

  public JdbcOrderRepository(JdbcOperations jdbcOperations) {
    this.jdbcOperations = jdbcOperations;
  }

  @Override
  public TacoOrder save(TacoOrder order) {
    StringBuilder insertStmt = new StringBuilder();
    insertStmt.append("insert into Taco_Order ");
    insertStmt.append("(cust_name, deli_zip, deli_road_addr, ");
    insertStmt.append("deli_detail_addr, cc_number, cc_cvv, ");
    insertStmt.append("cc_expr_y_m, placed_at) ");
    insertStmt.append("values (?,?,?,?,?,?,?,?)");

    PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(
        insertStmt.toString(),
        Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
        Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
        Types.VARCHAR, Types.TIMESTAMP);
    pscf.setReturnGeneratedKeys(true);

    order.setPlacedAt(LocalDateTime.now());
    PreparedStatementCreator psc = pscf.newPreparedStatementCreator(
        Arrays.asList(
            order.getCustName(),
            order.getDeliZip(),
            order.getDeliRoadAddr(),
            order.getDeliDetailAddr(),
            order.getCcNumber(),
            order.getCcCvv(),
            order.getCcExprYM(),
            order.getPlacedAt()));

    GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcOperations.update(psc, keyHolder);
    long orderId = keyHolder.getKey().longValue();
    order.setId(orderId);

    List<Taco> tacos = order.getTacos();
    int i = 0;
    for (Taco taco : tacos) {
      saveTaco(orderId, i++, taco);
    }

    return order; // 실인자 대비, 주문시각 및 id 값이 채워짐.
  }

  private long saveTaco(long orderId, int orderKey, Taco taco) {
    taco.setCreatedAt(LocalDateTime.now());
    PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(
        "insert into Taco(name, order_id, order_key, created_at)"
            + " values (?, ?, ?, ?)",
        Types.VARCHAR, Type.LONG, Type.LONG, Types.TIMESTAMP);
    pscf.setReturnGeneratedKeys(true);

    PreparedStatementCreator psc = pscf.newPreparedStatementCreator(
        Arrays.asList(
            taco.getName(),
            orderId,
            orderKey,
            taco.getCreatedAt()));

    GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcOperations.update(psc, keyHolder);
    long tacoId = keyHolder.getKey().longValue();
    taco.setId(tacoId);

    saveIngredientRefs(tacoId, taco.getIngredients());

    return tacoId;
  }

  private void saveIngredientRefs(long tacoId, List<Ingredient> ingredients) {
    // TODO Auto-generated method stub

  }
}
