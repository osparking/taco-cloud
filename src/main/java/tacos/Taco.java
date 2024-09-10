package tacos;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Taco {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private LocalDateTime createdAt = LocalDateTime.now();

  @NotNull
  @Size(min = 2, message = "이름은 최소 2 문자라야 됩니다.")
  private String name;
  
  @NotNull
  private Long checkedWrapId;

  // @formatter:off
  @NotNull
  @Size(min = 1, message = "*타코 빵 이외의 재료를 한 가지 이상 선택하세요.")
  @ManyToMany
  @JoinTable(name = "taco_ingredients", 
    joinColumns = @JoinColumn(name = "taco_id"), 
    inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
  private List<Ingredient> includedIngredients = new ArrayList<>();
  // @formatter:on
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "order_id")
  @JsonIgnore
  private TacoOrder order;

  @Override
  public String toString() {
    return "Taco [id=" + id + ", createdAt=" + createdAt + ", name=" + name
        + "]";
  }
}
