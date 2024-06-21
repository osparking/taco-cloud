package tacos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class IngredientRef {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private final short ingredientSN; // 재료 일련번호
}
