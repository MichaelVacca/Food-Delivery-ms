package com.fooddelivery.apigateway.presentationLayer;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Data
@AllArgsConstructor
public class RestaurantMenuResponseModel extends RepresentationModel<RestaurantMenuResponseModel> {

    private final String restaurantId;
    private final String restaurantName;
    private List<MenuResponseModel> allItems;



}



