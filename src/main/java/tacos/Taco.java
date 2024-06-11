package tacos;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Taco {
  @NotNull
  @Size(min = 2, message = "이름은 최소 2 문자라야 됩니다.")
  private String name;

  @NotNull
  @Size(min = 1, message = "재료를 한 가지 이상 선택하세요.")
  private List<Ingredient> ingredients;
}
