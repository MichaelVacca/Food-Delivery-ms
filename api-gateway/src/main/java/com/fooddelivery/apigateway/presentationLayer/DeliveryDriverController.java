package com.fooddelivery.apigateway.presentationLayer;



import com.fooddelivery.apigateway.businessLayer.DeliveryDriverService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("api/v1/deliveryDrivers")
public class DeliveryDriverController {

    private DeliveryDriverService deliveryDriverService;

    public DeliveryDriverController(DeliveryDriverService deliveryDriverService) {
        this.deliveryDriverService = deliveryDriverService;
    }

    @GetMapping(value = "/{deliveryDriverId}", produces = "application/json")
    ResponseEntity<DeliveryDriverResponseModel> getDeliveryDriverAggregate(@PathVariable String deliveryDriverId) {
        log.debug("1, Received in api-gateway deliveryDriversController getDeliveryDriverAggregate with deliverDriverId: " + deliveryDriverId);
        return  ResponseEntity.ok().body(deliveryDriverService.getDeliveryDriverAggregate(deliveryDriverId));
    }
}
