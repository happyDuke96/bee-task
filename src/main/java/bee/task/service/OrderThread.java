package bee.task.service;


import bee.task.model.ProductOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

@Slf4j
public class OrderThread implements Runnable {

    private final List<ProductOrder> productOrders;
    private final ProductOrder order;
    private final CyclicBarrier cyclicBarrier;
    @Autowired
    ProductOrderService orderService;

    public OrderThread(ProductOrder order, CyclicBarrier cyclicBarrier) {
        this.order = order;
        this.cyclicBarrier = cyclicBarrier;
        this.productOrders = new ArrayList<>();
    }

    @Override
    public void run() {
        try {
            log.info("Taking order");
            Thread.sleep(2000);
            productOrders.add(order);
            saveAll(productOrders);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveAll(List<ProductOrder> productOrders) {
        try {
            cyclicBarrier.await();
            log.info("Data saving");
        } catch (InterruptedException | BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
        orderService.saveAll(productOrders);
    }
}
