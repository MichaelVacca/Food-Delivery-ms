package com.fooddelivery.ordersservice.presentationlayer;

import com.fooddelivery.ordersservice.datalayer.Items;
import com.fooddelivery.ordersservice.datalayer.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class OrderResponseModel {
    private String orderId;
    private String clientId;
    private String restaurantId;
    private String menuId;
    private String deliveryDriverId;
    private String driverFirstName;
    private String driverLastName;
    private String clientUsername;
    private String clientEmail;
    private List<Items> items;
    private String restaurantName;
    private String typeOfMenu;
    private OrderStatus orderStatus;
    private Double finalPrice;
    private String estimatedDeliveryTime;
    private LocalDate orderDate;

}
