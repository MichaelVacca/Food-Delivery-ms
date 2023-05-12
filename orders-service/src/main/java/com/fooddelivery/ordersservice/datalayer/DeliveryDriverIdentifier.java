package com.fooddelivery.ordersservice.datalayer;



import java.util.UUID;


public class DeliveryDriverIdentifier {
    private String deliveryDriverId;

    public DeliveryDriverIdentifier(String deliveryDriverId) {
        this.deliveryDriverId = UUID.randomUUID().toString();
    }

    public String getDeliveryDriverId() {
        return deliveryDriverId;
    }
}
