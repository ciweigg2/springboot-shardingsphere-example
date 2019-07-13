package com.ciwei;

import com.ciwei.model.Orders;
import com.ciwei.model.OrdersDetail;
import com.ciwei.service.OrdersDetailService;
import com.ciwei.service.OrdersService;
import com.ciwei.util.SnowFlake;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Ciwei
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class InitData {
    static int nThreads = 100;
    private static ExecutorService pool = Executors.newFixedThreadPool(nThreads);
    @Autowired
    private OrdersService ordersService;
    @Autowired
    private OrdersDetailService ordersDetailService;
    private SnowFlake snowFlake = new SnowFlake(0, 0);

    @Test
    public void batchInitData() {
        for (int i = 0; i < 200000; i++) {
            String year = "2019";
            if (i == 100000) {
                year = "2020";
            }
            Orders orders = new Orders();
            String orderId = year + String.valueOf(snowFlake.nextId());
            orders.setId(orderId);
            orders.setAdddate(new Date());
            orders.setOrderType("1");
            orders.setOrderOrigin("2");
            orders.setParentOrdersId("222211" + (new Random().nextInt(1000)));
            orders.setParentOrdersUuid("333333");
            ordersService.saveOrders(orders);

            OrdersDetail ordersDetail = new OrdersDetail();
            ordersDetail.setId(String.valueOf(snowFlake.nextId()));
            ordersDetail.setOrdersId(orderId);
            ordersDetail.setGoodsId((new Random().nextInt(1000) + "3333"));
            ordersDetail.setGoodsName("测试商品" + (new Random().nextInt(1000)));
            ordersDetailService.saveOrderDetail(ordersDetail);
        }
    }

    CountDownLatch latch = new CountDownLatch(200000);

    @Test
    public void initData() throws InterruptedException {
        AtomicInteger atomicInteger = new AtomicInteger();
        for (int i = 0; i < 200000; i++) {
            String year = "2019";
            if (atomicInteger.get() == 100000) {
                year = "2020";
            }
            String finalYear = year;
            pool.execute(() -> {
                atomicInteger.getAndIncrement();
                Orders orders = new Orders();
                String orderId = finalYear + String.valueOf(snowFlake.nextId());
                orders.setId(orderId);
                orders.setAdddate(new Date());
                orders.setOrderType("1");
                orders.setOrderOrigin("2");
                orders.setParentOrdersId("222211" + (new Random().nextInt(1000)));
                orders.setParentOrdersUuid("333333");
                ordersService.saveOrders(orders);

                OrdersDetail ordersDetail = new OrdersDetail();
                ordersDetail.setId(String.valueOf(snowFlake.nextId()));
                ordersDetail.setOrdersId(orderId);
                ordersDetail.setGoodsId((new Random().nextInt(1000) + "3333"));
                ordersDetail.setGoodsName("测试商品" + (new Random().nextInt(1000)));
                ordersDetailService.saveOrderDetail(ordersDetail);
                latch.countDown();
            });
        }
        latch.await();
//        关闭线程池
        pool.shutdown();
    }

}
