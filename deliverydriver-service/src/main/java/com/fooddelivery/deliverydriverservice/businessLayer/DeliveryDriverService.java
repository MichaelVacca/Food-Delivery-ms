package com.fooddelivery.deliverydriverservice.businessLayer;



import com.fooddelivery.deliverydriverservice.datalayer.DeliveryDriver;
import com.fooddelivery.deliverydriverservice.presentationlayer.DeliveryDriverResponseModel;

import java.util.List;

public interface DeliveryDriverService {

    List<DeliveryDriverResponseModel> getAllDeliveryDrivers();

    DeliveryDriver getDeliveryDriversById(String deliveryDriverId);

    DeliveryDriver addNewDeliveryDriver(DeliveryDriver newDeliveryDriver);

    DeliveryDriver updateExistingDeliveryDriver(DeliveryDriver deliveryDriver, String deliveryDriverId );

    void deleteExistingDeliveryDriver(String deliveryDriverId);
}
