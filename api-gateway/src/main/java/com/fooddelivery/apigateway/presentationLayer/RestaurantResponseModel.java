package com.fooddelivery.apigateway.presentationLayer;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@Data
@AllArgsConstructor
public class RestaurantResponseModel extends RepresentationModel<RestaurantResponseModel> {
    private final String restaurantId;
    private final String restaurantName;
    private final String countryName;
    private final String streetName;
    private final String provinceName;
    private final String cityName;
    private final String postalCode;
}
