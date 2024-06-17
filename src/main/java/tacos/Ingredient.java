package tacos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient {
  private String id;
  private String name;
  private Type type;
  
  public enum Type {
    WRAP("빵"), PROTEIN("단백질"), VEGGIES("야채"), CHEESE("치즈"), SAUCE("소스");

    String desc;
    Type(String string) {
      this.desc = string;
    }

    public String getDesc() {
      return desc;
    }
  }
}
