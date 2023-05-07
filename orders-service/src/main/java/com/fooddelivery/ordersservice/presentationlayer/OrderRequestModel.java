package com.fooddelivery.ordersservice.presentationlayer;

import com.fooddelivery.ordersservice.datalayer.Items;

import java.util.List;

public class OrderRequestModel {
    private String restaurantId;
    private String menuId;
    private List<Items> items;
    private String driverId;
}
