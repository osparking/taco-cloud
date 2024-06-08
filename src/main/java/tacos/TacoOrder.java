package tacos;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class TacoOrder {
  private String custName; // 고객명
  private List<Taco> tacos = new ArrayList<>();
  private String deliZip; // 우편번호
  private String deliRoadAddr; // 도로 주소
  private String deliDetailAddr; // 상세 주소
  private String ccNumber;
  private String ccCvv;
  private String ccExprYM; // 만기년월

  public void addTaco(Taco taco) {
    tacos.add(taco);
  }
}
