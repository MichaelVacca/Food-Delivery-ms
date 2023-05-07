package com.fooddelivery.restaurantservice.Presentationlayer;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

import java.util.List;

@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RestaurantMenuResponseModel {

    private final String restaurantId;
    private final String restaurantName;
    private List<MenuResponseModel> allItems;



}



