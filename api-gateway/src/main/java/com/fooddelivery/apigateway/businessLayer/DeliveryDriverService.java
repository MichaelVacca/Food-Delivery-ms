package com.fooddelivery.apigateway.businessLayer;

import com.fooddelivery.apigateway.presentationLayer.DeliveryDriverResponseModel;

public interface DeliveryDriverService {
    DeliveryDriverResponseModel getDeliveryDriverAggregate(String deliveryDriverId);

}
