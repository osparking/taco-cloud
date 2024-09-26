package tacos.entity;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import tacos.TacoOrder;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@RequiredArgsConstructor
@AllArgsConstructor
public class TacoUser implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long userId;

  @Column(unique = true)
  private final String username;
  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private final String password;
  private final String custName;
  private final String custZip;
  private final String roadAddr;
  private final String detailAddr;
  private final String phoneNumber;
  @ToString.Exclude
  private int orderCount;

  @Transient
  private List<GrantedAuthority> authorities =
          Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));

  public int getOrderCount() {
    return tacoOrders.size();
  }

  @OneToMany(mappedBy = "tacoUser")
  private List<TacoOrder> tacoOrders;

  public TacoUser(String username, String password, String authority) {
    this.username = username;
    this.password = password;
    this.custName = username;
    this.custZip = username;
    this.roadAddr = username;
    this.detailAddr = username;
    this.phoneNumber = username;
    this.authorities = Arrays.asList(new SimpleGrantedAuthority(authority));
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public String toString() {
    return "TacoUser{" +
            "userId=" + userId +
            ", username='" + username + '\'' +
            ", password='" + password + '\'' +
            ", custName='" + custName + '\'' +
            ", custZip='" + custZip + '\'' +
            ", roadAddr='" + roadAddr + '\'' +
            ", detailAddr='" + detailAddr + '\'' +
            ", phoneNumber='" + phoneNumber + '\'' +
            '}';
  }
}
