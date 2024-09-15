package tacos;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@ToString
public class Ingredient {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true)
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

  @ManyToMany(mappedBy = "includedIngredients")
  @JsonIgnore
  List<Taco> includingTacos = new ArrayList<>();
}
