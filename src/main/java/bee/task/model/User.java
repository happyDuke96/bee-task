package bee.task.model;

import bee.task.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Table(name = "product_order")
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User implements Serializable {

    static final long serialVersionUID = 3L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;


    @Column(name = "full_name")
    String fullName;

    @Column(name = "user_name")
    String userName;

    @JsonIgnore
    String password;

    @Column(name = "address")
    String address;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    Role role;

}
