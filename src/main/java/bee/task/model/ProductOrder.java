package bee.task.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductOrder implements Serializable {

    static final long serialVersionUID = 1L;

    Integer userId;

    Integer productId;

    LocalDateTime orderTime;

    public ProductOrder(Integer userId, Integer productId, LocalDateTime orderTime) {
        this.userId = userId;
        this.productId = productId;
        this.orderTime = LocalDateTime.now();
    }

    public Order toEntity() {
        Order order = new Order();
        order.setProductId(this.getProductId());
        order.setUserId(this.getUserId());
        order.setOrderTime(this.getOrderTime());
        return order;
    }
}
