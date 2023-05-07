package com.fooddelivery.apigateway.domainClientLayer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fooddelivery.apigateway.presentationLayer.DeliveryDriverResponseModel;
import com.fooddelivery.apigateway.utils.HttpErrorInfo;
import com.fooddelivery.apigateway.utils.exceptions.InvalidInputException;
import com.fooddelivery.apigateway.utils.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;


@Slf4j
@Component
public class DeliveryDriverServiceClient {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final String DELIVERY_DRIVER_SERVICE_BASE_URL;

    public DeliveryDriverServiceClient(RestTemplate restTemplate,
                                       ObjectMapper objectMapper,
                                       @Value("${app.deliverydriver-service.host}") String deliveryDriverServiceHost,
                                       @Value("${app.deliverydriver-service.port}") String deliveryDriverServicePort) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
        this.DELIVERY_DRIVER_SERVICE_BASE_URL = "http://" + deliveryDriverServiceHost + ":" + deliveryDriverServicePort + "/api/v1/api/v1/deliveryDrivers";
    }

    public DeliveryDriverResponseModel getDeliveryDrivers(String deliveryDriverId) {
        DeliveryDriverResponseModel deliveryDriverResponseModel;
        try {
            String url = DELIVERY_DRIVER_SERVICE_BASE_URL + "/" + deliveryDriverId;
            deliveryDriverResponseModel = restTemplate
                    .getForObject(url, DeliveryDriverResponseModel.class);

            log.debug("5. Received in API-Gateway DeliveryDriver Service Client getDeliveryDriverAggregate with deliveryDriverResponseModel : " + deliveryDriverResponseModel.getDeliveryDriverId());
        } catch (HttpClientErrorException ex) {
            log.debug("5.");
            throw handleHttpClientException(ex);
        }
        return deliveryDriverResponseModel;
    }

    private RuntimeException handleHttpClientException(HttpClientErrorException ex) {
        if (ex.getStatusCode() == NOT_FOUND) {
            return new NotFoundException(getErrorMessage(ex));
        }
        if (ex.getStatusCode() == UNPROCESSABLE_ENTITY) {
            return new InvalidInputException(getErrorMessage(ex));
        }
        log.warn("Got a unexpected HTTP error: {}, will rethrow it", ex.getStatusCode());
        log.warn("Error body: {}", ex.getResponseBodyAsString());
        return ex;
    }
    private String getErrorMessage(HttpClientErrorException ex) {
        try {
            return objectMapper.readValue(ex.getResponseBodyAsString(), HttpErrorInfo.class).getMessage();
        }
        catch (IOException ioex) {
            return ioex.getMessage();
        }
    }
}
