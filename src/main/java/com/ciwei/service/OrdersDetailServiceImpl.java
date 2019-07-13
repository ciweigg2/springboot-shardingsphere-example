package com.ciwei.service;

import com.ciwei.mapper.sharding.OrdersDetailMapper;
import com.ciwei.model.OrdersDetail;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ciwei
 */
@Service
@Slf4j
public class OrdersDetailServiceImpl implements OrdersDetailService {

    @Autowired
    private OrdersDetailMapper ordersDetailMapper;

    @Override
    public boolean saveOrderDetail(OrdersDetail ordersDetail) {
        if (ordersDetail == null) {
            return false;
        }
        int result = ordersDetailMapper.insertDetail(ordersDetail);
        return result == 1 ? true : false;
    }

    @Override
    public List<OrdersDetail> getDetailByOrderId(String orderId) {
        if (StringUtils.isEmpty(orderId)) {
            return null;
        }
        return ordersDetailMapper.selectDetailByOrderId(orderId);
    }

}
