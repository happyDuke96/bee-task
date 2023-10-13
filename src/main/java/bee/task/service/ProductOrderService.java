package bee.task.service;


import bee.task.model.Order;
import bee.task.model.ProductOrder;
import bee.task.repository.ProductOrderRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductOrderService {
    private final ProductOrderRepository orderRepository;
    private ExecutorService threadPool;
    private CyclicBarrier cyclicBarrier;

    @PostConstruct
    public void init() {
        threadPool = Executors.newFixedThreadPool(5);
        cyclicBarrier = new CyclicBarrier(5);
    }

    @PreDestroy
    public void destroy() {
        threadPool.shutdown();
        try {
            threadPool.awaitTermination(1L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public ProductOrderService(ProductOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void saveAll(List<ProductOrder> orders) {
        List<Order> resultList = orders.stream().map(ProductOrder::toEntity).collect(Collectors.toList());
        orderRepository.saveAll(resultList);
    }

    @RabbitListener(queues = {"${rabbit.queue}"})
    public void consume(ProductOrder message) throws InterruptedException {
        threadPool.submit(new OrderThread(message, cyclicBarrier));
    }
}
