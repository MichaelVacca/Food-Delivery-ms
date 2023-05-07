package com.fooddelivery.apigateway.businessLayer;

import com.fooddelivery.apigateway.presentationLayer.ClientResponseModel;

public interface ClientsService {

    ClientResponseModel getClient(String clientId);
}
