package com.fooddelivery.ordersservice.businesslayer;

import com.fooddelivery.ordersservice.presentationlayer.OrderResponseModel;
import java.util.List;

public interface OrderService {
    List<OrderResponseModel> getAllPurchaseOrders();

    OrderResponseModel processClientOrders(OrderResponseModel orderResponseModel, String clientId);

}
