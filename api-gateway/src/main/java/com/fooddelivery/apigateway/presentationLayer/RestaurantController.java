package com.fooddelivery.apigateway.presentationLayer;


import com.fooddelivery.apigateway.businessLayer.RestaurantService;

import com.fooddelivery.apigateway.utils.exceptions.InvalidInputException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
@Slf4j
@RestController
@RequestMapping("api/v1/restaurants")
public class RestaurantController {

    private final Integer UUID_SIZE = 36;
    private RestaurantService restaurantService;


    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping(value="/{restaurantId}", produces = "application/json")
    ResponseEntity<RestaurantMenuResponseModel> getRestaurantAggregate(@PathVariable String restaurantId){
        if(restaurantId.length()!=UUID_SIZE){
            throw new InvalidInputException("Restaurant id is invalid: " + restaurantId);
        }
        log.debug("1. Received in Restaurant Controller getRestaurantMenuAggregate with restaurantId: " + restaurantId);
        return ResponseEntity.ok().body(restaurantService.getRestaurantAggregate(restaurantId));
    }

}
