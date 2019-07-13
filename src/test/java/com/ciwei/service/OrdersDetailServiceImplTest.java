package com.ciwei.service;

import com.ciwei.mapper.sharding.OrdersDetailMapper;
import com.ciwei.model.OrdersDetail;
import com.ciwei.util.SnowFlake;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Ciwei
 */
public class OrdersDetailServiceImplTest extends BaseServiceTest{
    @Autowired
    private OrdersDetailService ordersDetailService;
    private SnowFlake snowFlake = new SnowFlake(2,3);
    @Autowired
    private OrdersService ordersService;
    @Autowired
    private OrdersDetailMapper ordersDetailMapper;
    @Test
    public void saveOrderDetail() {
        OrdersDetail ordersDetail = new OrdersDetail();
        ordersDetail.setId("111");
        ordersDetail.setGoodsId("2222");
        ordersDetail.setOrdersId("201905071222");
        ordersDetail.setGoodsName("测试商品");
        ordersDetail.setGoodsKindname("测试类目");
        ordersDetailService.saveOrderDetail(ordersDetail);
    }

    @Test
    public void getDetailByOrderId() {
        String orderId = "2222";
        List<OrdersDetail> detailByOrderId = ordersDetailService.getDetailByOrderId(orderId);
        System.out.println("--->返回的数据是"+detailByOrderId);
    }
    @Test
    public void getDetailById() {
        String id = "3333";
        List<OrdersDetail> detailByOrderId = ordersDetailMapper.selectDetailById(id);
        System.out.println("--->返回的数据是"+detailByOrderId);
    }
}