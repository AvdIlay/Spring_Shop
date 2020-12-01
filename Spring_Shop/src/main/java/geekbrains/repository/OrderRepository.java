package geekbrains.repository;

import geekbrains.entities.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> findAll();
    List<Order> findAllByUserId(Long id);
}
