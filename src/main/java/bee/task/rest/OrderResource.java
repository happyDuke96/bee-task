package bee.task.rest;

import bee.task.model.ProductOrder;
import bee.task.service.RabbitMQProducer;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
@Slf4j
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class OrderResource {

    RabbitMQProducer producer;
    @PostMapping("/create")
    public ResponseEntity<Void> create(@RequestBody ProductOrder order) {
        log.info("Sending request to create Order");
        producer.sendMessage(order);
        return ResponseEntity.ok().build();
    }



}
