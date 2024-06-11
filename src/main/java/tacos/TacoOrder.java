package tacos;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.CreditCardNumber;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class TacoOrder {
  @NotBlank(message="고객 성명은 필수 입력 항목입니다.")
  private String custName; // 고객명
  
  private List<Taco> tacos = new ArrayList<>();
  
  @NotBlank(message="우편번호는 필수 입력 항목입니다.")
  private String deliZip; // 우편번호
  
  @NotBlank(message="도로 주소는 필수 입력 항목입니다.")
  private String deliRoadAddr; // 도로 주소

  @NotBlank(message="상세 주소는 필수 입력 항목입니다.")
  private String deliDetailAddr; // 상세 주소
  
  @CreditCardNumber(message="신용카드 번호가 정당하지 않습니다.")
  private String ccNumber;
  
  @NotBlank(message=" 필수 입력 항목입니다.")
  @Digits(integer=3, fraction=0, message="CVV 입력 형식이 잘못되었습니다.")
  private String ccCvv;
  
  @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([2-9][0-9])$",
      message="만기년월 형식은 '월월/년년'입니다.")  
  private String ccExprYM; // 만기년월

  public void addTaco(Taco taco) {
    tacos.add(taco);
  }
}
