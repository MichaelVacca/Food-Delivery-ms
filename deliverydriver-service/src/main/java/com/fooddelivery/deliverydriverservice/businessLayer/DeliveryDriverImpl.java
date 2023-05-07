package com.fooddelivery.deliverydriverservice.businessLayer;


import com.fooddelivery.deliverydriverservice.dataMapperlayer.DeliveryDriverResponseMapper;
import com.fooddelivery.deliverydriverservice.datalayer.DeliveryDriver;
import com.fooddelivery.deliverydriverservice.datalayer.DeliveryDriverRepository;
import com.fooddelivery.deliverydriverservice.presentationlayer.DeliveryDriverResponseModel;
import com.fooddelivery.deliverydriverservice.utils.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryDriverImpl implements DeliveryDriverService{

    private DeliveryDriverRepository deliveryDriverRepository;
    private DeliveryDriverResponseMapper deliveryDriverResponseMapper;

    public DeliveryDriverImpl(DeliveryDriverRepository deliveryDriverRepository, DeliveryDriverResponseMapper deliveryDriverResponseMapper) {
        this.deliveryDriverRepository = deliveryDriverRepository;
        this.deliveryDriverResponseMapper = deliveryDriverResponseMapper;
    }

    @Override
    public List<DeliveryDriverResponseModel> getAllDeliveryDrivers() {
        return deliveryDriverResponseMapper.entityListToResponseModelList(deliveryDriverRepository.findAll());
    }


    @Override
    public DeliveryDriver getDeliveryDriversById(String deliveryDriverId) {
        return deliveryDriverRepository.findByDeliveryDriverIdentifier_DeliveryDriverId(deliveryDriverId);
    }

    @Override
    public DeliveryDriver addNewDeliveryDriver(DeliveryDriver newDeliveryDriver) {
        return deliveryDriverRepository.save(newDeliveryDriver);
    }

    @Override
    public DeliveryDriver updateExistingDeliveryDriver(DeliveryDriver deliveryDriver, String deliveryDriverId) {

        DeliveryDriver existingDeliveryDriver = deliveryDriverRepository.findByDeliveryDriverIdentifier_DeliveryDriverId(deliveryDriverId);

        if(existingDeliveryDriver == null){
            throw new NotFoundException("Deliver driver with id: " + deliveryDriverId +" not found");
        }

        deliveryDriver.setId(existingDeliveryDriver.getId());
        deliveryDriver.setDeliveryDriverIdentifier(existingDeliveryDriver.getDeliveryDriverIdentifier());

        return deliveryDriverRepository.save(deliveryDriver);
    }


    @Override
    public void deleteExistingDeliveryDriver(String deliveryDriverId) {

        DeliveryDriver existingDeliveryDriver = deliveryDriverRepository.findByDeliveryDriverIdentifier_DeliveryDriverId(deliveryDriverId);

        if(existingDeliveryDriver == null){
            throw new NotFoundException("Deliver driver with id: " + deliveryDriverId +" not found");
        }
        deliveryDriverRepository.delete(existingDeliveryDriver);
    }


}
