package tacos.data;

import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.Data;
import tacos.entity.TacoUser;

@Data
public class RegistrationForm {

  private String username;
  private String password;
  private String custName;
  private String custZip;
  private String roadAddr;
  private String detailAddr;
  private String phoneNumber;

  public TacoUser toUser(PasswordEncoder passwordEncoder) {
    return new TacoUser(
        username, passwordEncoder.encode(password),
        custName, custZip, roadAddr, detailAddr, phoneNumber);
  }

}