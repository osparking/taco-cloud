package tacos.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="taco.orders")
@Data
public class MyProperties {
    /**
     * 타코 주문 목록 페이지 크기(주문 건수)
     */
    public int pageSize;
}
