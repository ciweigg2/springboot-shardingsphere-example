package com.ciwei;

import com.ciwei.model.Orders;
import com.ciwei.service.OrdersService;
import com.ciwei.util.OrderIdGenerator;
import com.ciwei.util.SnowFlake;
import com.ciwei.util.UUIDutil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableTransactionManagement(proxyTargetClass = true)
@RestController
public class SpringbootShardingsphereExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootShardingsphereExampleApplication.class, args);
        System.out.println("------>springboot启动成功");
    }

    @Autowired
    private OrdersService ordersService;

    @RequestMapping(value = "addOrders")
    public ResponseEntity addOrders() {
        Orders orders = new Orders();
        SnowFlake snowFlake = new SnowFlake(0, 1);
        orders.setId(OrderIdGenerator.getLongOrderId(StringUtils.replace(UUID.randomUUID().toString(), "-", "")));
        orders.setAdddate(new Date());
        orders.setParentOrdersUuid(UUIDutil.getUUID());
        orders.setParentOrdersId(UUIDutil.getUUID());
        ordersService.saveOrders(orders);
        return ResponseEntity.ok(orders);
    }

    @RequestMapping(value = "getOrders")
    public ResponseEntity getOrders(String orderId) {
        return ResponseEntity.ok(ordersService.getOrderById(orderId));
    }

}
