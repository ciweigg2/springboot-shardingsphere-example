package com.ciwei.mapper.sharding;

import com.ciwei.model.OrdersDetail;

import java.util.List;

/**
 * @author Ciwei
 */
public interface OrdersDetailMapper {

    /**
     * @param ordersDetail
     * @return
     */
    int insertDetail(OrdersDetail ordersDetail);

    /**
     * 根据订单ID查询订单明细
     * @param orderId
     * @return
     */
    List<OrdersDetail> selectDetailByOrderId(String orderId);

    /**
     * @param id
     * @return
     */
    List<OrdersDetail> selectDetailById(String id);
}
