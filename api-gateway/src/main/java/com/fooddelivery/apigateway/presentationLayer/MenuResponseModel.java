package com.fooddelivery.apigateway.presentationLayer;


import lombok.*;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Data
@AllArgsConstructor
public class MenuResponseModel extends RepresentationModel<MenuResponseModel> {
    private final String restaurantId;
    private final String menuId;
    private final String typeOfMenu;
    private final List<Items> items;


}
