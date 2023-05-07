package com.fooddelivery.ordersservice.presentationlayer;

import com.fooddelivery.ordersservice.datalayer.Items;

import java.time.LocalDate;
import java.util.List;

public class OrderResponseModel {
    private String clientId;
    private String restaurantId;
    private String menuId;
    private List<Items> items;
    private String driverId;
    private String restaurantName;
    private String typeOfMenu;
    private LocalDate orderDate;
    private Double finalPrice;
    private double estimatedDeliveryTime;

}
