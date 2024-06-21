package tacos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
public class Ingredient {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private final String code;
  private final String name;
  private final Short typeOrd;
  
  public enum Type {
    WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
  }

  public Ingredient(String code, String name, short typeOrd) {
    super();
    this.code = code;
    this.name = name;
    this.typeOrd = typeOrd;
  }
}
