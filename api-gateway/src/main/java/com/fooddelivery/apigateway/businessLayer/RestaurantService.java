package com.fooddelivery.apigateway.businessLayer;

import com.fooddelivery.apigateway.presentationLayer.*;

public interface RestaurantService {
    RestaurantMenuResponseModel getRestaurantAggregate(String restaurantId);

    RestaurantResponseModel[] getAllRestaurantsAggregate();
    RestaurantResponseModel addRestaurant(RestaurantRequestModel restaurantRequestModel);
    MenuResponseModel addMenuToRestaurant(String restaurantId, MenuRequestModel newMenu);
    void updateMenuInRestaurantByMenuId(MenuRequestModel menuRequestModel,String restaurantId,String menuId);
    void updateRestaurantAggregate(String restaurantId,RestaurantRequestModel restaurantRequestModel);
    void deleteMenuFromInventory(String restaurantId, String menuId);
    void deleteRestaurantAggregate(String restaurantId);

}
