package com.fooddelivery.apigateway.businessLayer;

import com.fooddelivery.apigateway.domainClientLayer.DeliveryDriverServiceClient;
import com.fooddelivery.apigateway.presentationLayer.DeliveryDriverResponseModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DeliveryDriverServiceImpl implements DeliveryDriverService{

    private DeliveryDriverServiceClient deliveryDriverServiceClient;

    public DeliveryDriverServiceImpl(DeliveryDriverServiceClient deliveryDriverServiceClient) {
        this.deliveryDriverServiceClient = deliveryDriverServiceClient;
    }

    @Override
    public DeliveryDriverResponseModel getDeliveryDriverAggregate(String deliveryDriverId) {
        return deliveryDriverServiceClient.getDeliveryDrivers(deliveryDriverId);
    }
}
