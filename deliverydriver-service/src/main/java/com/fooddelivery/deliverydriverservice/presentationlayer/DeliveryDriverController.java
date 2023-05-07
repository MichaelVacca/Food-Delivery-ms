package com.fooddelivery.deliverydriverservice.presentationlayer;



import com.fooddelivery.deliverydriverservice.businessLayer.DeliveryDriverService;
import com.fooddelivery.deliverydriverservice.datalayer.DeliveryDriver;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/deliveryDrivers")
public class DeliveryDriverController {
    private DeliveryDriverService deliveryDriverService;

    public DeliveryDriverController(DeliveryDriverService deliveryDriverService) {
        this.deliveryDriverService = deliveryDriverService;
    }

    @GetMapping()
    public List<DeliveryDriverResponseModel> getDeliveryDrivers(){
        return deliveryDriverService.getAllDeliveryDrivers();
    }

    @GetMapping("/{deliveryDriverId}")
    public DeliveryDriver getDeliveryDriversById(@PathVariable String deliveryDriverId){
        return deliveryDriverService.getDeliveryDriversById(deliveryDriverId);
    }

    @PostMapping()
    public DeliveryDriver addDeliveryDriver(@RequestBody DeliveryDriver newDeliveryDriver){
        return deliveryDriverService.addNewDeliveryDriver(newDeliveryDriver);
    }

    @PutMapping("/{deliveryDriverId}")
    public DeliveryDriver updateDeliveryDriverInfo(@RequestBody DeliveryDriver deliveryDriver,
                                                   @PathVariable String deliveryDriverId){
        return deliveryDriverService.updateExistingDeliveryDriver(deliveryDriver,deliveryDriverId);

    }

    @DeleteMapping("/{deliveryDriverId}")
    public void deleteDeliveryDriverById(@PathVariable String deliveryDriverId){
        deliveryDriverService.deleteExistingDeliveryDriver(deliveryDriverId);
    }

















}
