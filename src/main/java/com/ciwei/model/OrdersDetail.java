package com.ciwei.model;

import lombok.Data;

/**
 * @author Ciwei
 */
@Data
public class OrdersDetail {
    /**
     * 订单明细id
     */
    private String id;

    /**
     * 订单id
     */
    private String ordersId;
    /**
     * 商品id
     */
    private String goodsId;
    /**
     * 商品名称
     */
    private String goodsName;
    /**
     * 商品属性
     */
    private String goodsKindname;
}
