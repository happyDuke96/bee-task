package bee.task.service;

import bee.task.model.ProductOrder;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RabbitMQProducer {

    @Value("${rabbit.exchange}")
    String exchange;

    @Value("${rabbit.routing.key}")
    String routingKey;

    RabbitTemplate rabbitTemplate;
    public RabbitMQProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(ProductOrder message){
        rabbitTemplate.convertAndSend(exchange, routingKey, message);
    }
}
