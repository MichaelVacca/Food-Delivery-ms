package com.fooddelivery.apigateway.domainClientLayer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fooddelivery.apigateway.presentationLayer.ClientRequestModel;
import com.fooddelivery.apigateway.presentationLayer.ClientResponseModel;
import com.fooddelivery.apigateway.utils.HttpErrorInfo;
import com.fooddelivery.apigateway.utils.exceptions.InvalidInputException;
import com.fooddelivery.apigateway.utils.exceptions.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

@SpringBootTest
class ClientServiceClientTest {
    @MockBean
    private RestTemplate restTemplate;

    @Autowired
    private ClientServiceClient clientServiceClient;

    private String clientId = "c3540a89-cb47-4c96-888e-ff96708db4d8";
    private String baseUrl = "http://localhost:8080/api/v1/clients";


    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        // make sure to setup the RestTemplate, ObjectMapper, and base url correctly
        clientServiceClient = new ClientServiceClient(restTemplate, new ObjectMapper(), "localhost", "8080");
    }

    @Test
    public void getClientTest() {
        ClientResponseModel clientResponseModel = new ClientResponseModel(clientId, "1", "1", "1", "1", "1", "1", "1", "1", "1", "1");
        when(restTemplate.getForObject(baseUrl + "/" + clientId, ClientResponseModel.class)).thenReturn(clientResponseModel);

        ClientResponseModel result = clientServiceClient.getClient(clientId);
        assertEquals(result.getClientId(), clientId);
        assertEquals(result.getUserName(), "1");
        assertEquals(result.getPassword(), "1");
        assertEquals(result.getAge(), "1");
        assertEquals(result.getEmailAddress(), "1");
        assertEquals(result.getPhoneNumber(), "1");
        assertEquals(result.getCountryName(), "1");
        assertEquals(result.getStreetName(), "1");
        assertEquals(result.getCityName(), "1");
        assertEquals(result.getProvinceName(), "1");
        assertEquals(result.getPostalCode(), "1");

        verify(restTemplate, times(1)).getForObject(baseUrl + "/" + clientId, ClientResponseModel.class);
        // assert other fields...
    }
    @Test
    public void addClientTest() {
        ClientRequestModel clientRequestModel = ClientRequestModel.builder()
                .userName("1")
                .password("1")
                .age("1")
                .emailAddress("1")
                .phoneNumber("1")
                .countryName("1")
                .streetName("1")
                .cityName("1")
                .provinceName("1")
                .postalCode("1")
                .build();

        ClientResponseModel clientResponseModel = new ClientResponseModel(clientId, "1", "1", "1", "1", "1", "1", "1", "1", "1", "1");

        when(restTemplate.postForObject(baseUrl, clientRequestModel, ClientResponseModel.class)).thenReturn(clientResponseModel);

        ClientResponseModel result = clientServiceClient.addClient(clientRequestModel);
        assertEquals(result.getClientId(), clientId);
        // assert other fields...

        // Verify that postForObject method was called
        verify(restTemplate, times(1)).postForObject(baseUrl, clientRequestModel, ClientResponseModel.class);
    }

    @Test
    public void updateClientTest() {
        String clientId = "c3540a89-cb47-4c96-888e-ff96708db4d8";

        ClientRequestModel clientRequestModel = ClientRequestModel.builder()
                .userName("1")
                .password("1")
                .age("1")
                .emailAddress("1")
                .phoneNumber("1")
                .countryName("1")
                .streetName("1")
                .cityName("1")
                .provinceName("1")
                .postalCode("1")
                .build();

        doNothing().when(restTemplate).put(baseUrl + "/" + clientId, clientRequestModel, ClientResponseModel.class);

        clientServiceClient.updateClient(clientId, clientRequestModel);

        // Verify that put method was called
        verify(restTemplate, times(1)).put(baseUrl + "/" + clientId, clientRequestModel, ClientResponseModel.class);
    }

    @Test
    public void deleteClientTest() {
        String clientId = "c3540a89-cb47-4c96-888e-ff96708db4d8";
        doNothing().when(restTemplate).delete(baseUrl + "/" + clientId);

        clientServiceClient.deleteClient(clientId);

        // Verify that delete method was called
        verify(restTemplate, times(1)).delete(baseUrl + "/" + clientId);
    }
/*    @Test
    public void testGetClient_NotFoundException() {
        String clientId = "non-existing-id";
        HttpClientErrorException ex = HttpClientErrorException.create(HttpStatus.NOT_FOUND, "Not Found",
                HttpHeaders.EMPTY, null, null);

        when(restTemplate.getForObject(baseUrl + "/" + clientId, ClientResponseModel.class)).thenThrow(ex);

        Exception exception = assertThrows(NotFoundException.class, () ->
                clientServiceClient.getClient(clientId));

        assertTrue(exception.getMessage().contains("Not Found"));
    }*/

    @Test
    public void testGetClient_NotFoundException() throws JsonProcessingException {
        String clientId = "non-existing-id";
        HttpErrorInfo errorInfo = new HttpErrorInfo( HttpStatus.NOT_FOUND, "/api/v1/clients/" + clientId,"Not Found");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule()); // register JavaTimeModule to support Java 8 date/time types

        String errorInfoJson = "";
        try {
            errorInfoJson = objectMapper.writeValueAsString(errorInfo);
        } catch (JsonProcessingException e) {
            fail("Failed to serialize HttpErrorInfo: " + e.getMessage());
        }

        HttpClientErrorException ex = HttpClientErrorException.create(HttpStatus.NOT_FOUND, "Not Found",
                HttpHeaders.EMPTY, errorInfoJson.getBytes(), null);

        when(restTemplate.getForObject(baseUrl + "/" + clientId, ClientResponseModel.class)).thenThrow(ex);

        Exception exception = assertThrows(NotFoundException.class, () ->
                clientServiceClient.getClient(clientId));

        assertTrue(exception.getMessage().contains("Not Found"));
    }



    @Test
    public void testGetClient_InvalidInputException() {
        String clientId = "invalid-input";
        HttpClientErrorException ex = HttpClientErrorException.create(HttpStatus.UNPROCESSABLE_ENTITY, "Unprocessable Entity",
                HttpHeaders.EMPTY, null, null);

        when(restTemplate.getForObject(baseUrl + "/" + clientId, ClientResponseModel.class)).thenThrow(ex);

        Exception exception = assertThrows(InvalidInputException.class, () ->
                clientServiceClient.getClient(clientId));

        assertTrue(exception.getMessage().contains("Unprocessable Entity"));
    }

    @Test
    public void testAddClient_NotFoundException() throws JsonProcessingException {
        ClientRequestModel clientRequestModel = ClientRequestModel.builder()
                .userName("1")
                .password("1")
                .age("1")
                .emailAddress("1")
                .phoneNumber("1")
                .countryName("1")
                .streetName("1")
                .cityName("1")
                .provinceName("1")
                .postalCode("1")
                .build();

        HttpErrorInfo errorInfo = new HttpErrorInfo(HttpStatus.NOT_FOUND, "/api/v1/clients", "Not Found");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule()); // register JavaTimeModule to support Java 8 date/time types

        String errorInfoJson = "";
        try {
            errorInfoJson = objectMapper.writeValueAsString(errorInfo);
        } catch (JsonProcessingException e) {
            fail("Failed to serialize HttpErrorInfo: " + e.getMessage());
        }

        HttpClientErrorException ex = HttpClientErrorException.create(HttpStatus.NOT_FOUND, "Not Found",
                HttpHeaders.EMPTY, errorInfoJson.getBytes(), null);

        when(restTemplate.postForObject(baseUrl, clientRequestModel, ClientResponseModel.class)).thenThrow(ex);

        Exception exception = assertThrows(NotFoundException.class, () ->
                clientServiceClient.addClient(clientRequestModel));

        assertTrue(exception.getMessage().contains("Not Found"));
    }


    @Test
    public void testAddClient_InvalidInputException() {
        ClientRequestModel clientRequestModel = ClientRequestModel.builder()
                .userName("1")
                .password("1")
                .age("1")
                .emailAddress("1")
                .phoneNumber("1")
                .countryName("1")
                .streetName("1")
                .cityName("1")
                .provinceName("1")
                .postalCode("1")
                .build();

        HttpClientErrorException ex = HttpClientErrorException.create(HttpStatus.UNPROCESSABLE_ENTITY, "Unprocessable Entity",
                HttpHeaders.EMPTY, null, null);

        when(restTemplate.postForObject(baseUrl, clientRequestModel, ClientResponseModel.class)).thenThrow(ex);

        Exception exception = assertThrows(InvalidInputException.class, () ->
                clientServiceClient.addClient(clientRequestModel));

        assertTrue(exception.getMessage().contains("Unprocessable Entity"));
    }

// Similar tests can be written for updateClient and deleteClient methods as well.



}



