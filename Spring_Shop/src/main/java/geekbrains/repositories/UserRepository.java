package geekbrains.repositories;

import geekbrains.entities.Role;
import geekbrains.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findAll();

    List<User> findAllByroles(Role role);
}
