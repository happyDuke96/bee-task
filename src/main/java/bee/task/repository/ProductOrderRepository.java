package bee.task.repository;


import bee.task.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductOrderRepository extends JpaRepository<Order,Integer> {
}
