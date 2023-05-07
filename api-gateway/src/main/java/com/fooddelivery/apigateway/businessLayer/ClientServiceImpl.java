package com.fooddelivery.apigateway.businessLayer;

import com.fooddelivery.apigateway.domainClientLayer.ClientServiceClient;
import com.fooddelivery.apigateway.presentationLayer.ClientResponseModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ClientServiceImpl implements ClientsService {

    private ClientServiceClient clientServiceClient;

    public ClientServiceImpl(ClientServiceClient clientServiceClient) {
        this.clientServiceClient = clientServiceClient;
    }

    @Override
    public ClientResponseModel getClient(String clientId) {
        return clientServiceClient.getClient(clientId);
    }
}
