package tacos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Taco {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private LocalDateTime createdAt = LocalDateTime.now();

  @NotNull
  @Size(min = 2, message = "이름은 최소 2 문자라야 됩니다.")
  private String name;

  @NotNull
  @Size(min = 1, message = "재료를 한 가지 이상 선택하세요.")
  @ManyToMany
  private List<IngredientRef> ingredientRefs = new ArrayList<>();

  public void addIngredient(IngredientRef ingredientRef) {
    this.ingredientRefs.add(ingredientRef);
  }
}
