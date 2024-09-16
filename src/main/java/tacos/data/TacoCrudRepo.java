package tacos.data;

import org.springframework.data.repository.CrudRepository;
import tacos.Taco;

public interface TacoCrudRepo extends CrudRepository<Taco, Long> {
}
