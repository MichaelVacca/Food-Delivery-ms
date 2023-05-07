package com.fooddelivery.restaurantservice.Presentationlayer;

import com.fooddelivery.restaurantservice.Datalayer.Address;
import lombok.*;

@Value
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class RestaurantRequestModel {
    String restaurantName;

    //Address address;

    String countryName;
    String streetName;
    String provinceName;
    String cityName;
    String postalCode;

}
