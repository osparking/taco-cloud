package tacos;


import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient {
  @Id
  private Short sn;
  private String id;
  private String name;
  private Short typeOrd;
  
  public enum Type {
    WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
  }

  public Ingredient(String id, String name, short typeOrd) {
    super();
    this.id = id;
    this.name = name;
    this.typeOrd = typeOrd;
  }
}
