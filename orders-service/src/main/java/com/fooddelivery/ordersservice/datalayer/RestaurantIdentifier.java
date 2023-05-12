package com.fooddelivery.ordersservice.datalayer;



import java.util.UUID;

public class RestaurantIdentifier {
    private String restaurantId;

    public RestaurantIdentifier(String restaurantId){
        this.restaurantId = UUID.randomUUID().toString();
    }

    public String getRestaurantId() {
        return restaurantId;
    }


}
