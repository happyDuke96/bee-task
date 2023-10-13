package bee.task.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Table(name = "orders")
@Entity
public class Order implements Serializable {

    static final long serialVersionUID = 2L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    Integer userId;

    Integer productId;

    LocalDateTime orderTime;



}
