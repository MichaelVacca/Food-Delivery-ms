package com.fooddelivery.ordersservice.presentationlayer;

import com.fooddelivery.ordersservice.datalayer.Items;
import com.fooddelivery.ordersservice.datalayer.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;
import java.util.List;

@Builder
@AllArgsConstructor
@Value
public class OrderRequestModel {
     String restaurantId;
     String menuId;
     Double totalPrice;
     String deliveryDriverId;
     OrderStatus orderStatus;
      List<Items> items;
     LocalDate orderDate;
}
