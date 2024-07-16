package com.bxc.order.service;

/**
 * @author: Bixc
 * @date: 2024/07/15 09:19
 **/
public interface OrderService {

    boolean createOrder(String cityId, String platformId, String userId, String supplierId, String goodsId);

    void sendOrderlyMessage4Pkg(String userId, String orderId);

}
