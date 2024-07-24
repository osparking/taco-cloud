package tacos.data;

import org.springframework.data.repository.CrudRepository;

import tacos.entity.TacoUser;

public interface UserRepository extends CrudRepository<TacoUser, Long> {
  TacoUser findByUsername(String username);
}
