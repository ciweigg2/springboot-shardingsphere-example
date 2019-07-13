package com.ciwei.service;

import com.ciwei.model.OrdersDetail;

import java.util.List;

/**
 * @author Ciwei
 */
public interface OrdersDetailService {

    /**
     * @param ordersDetail
     * @return
     */
    boolean saveOrderDetail(OrdersDetail ordersDetail);

    /**
     * @param orderId
     * @return
     */
    List<OrdersDetail> getDetailByOrderId(String orderId);

}
