package com.fooddelivery.ordersservice.presentationlayer;

import com.fooddelivery.ordersservice.datalayer.Items;
import com.fooddelivery.ordersservice.datalayer.OrderStatus;

import java.time.LocalDate;
import java.util.List;

public class OrderResponseModel {
    private String clientId;
    private String restaurantId;
    private String menuId;
    private String driverId;
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
