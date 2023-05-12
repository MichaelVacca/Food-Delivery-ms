package com.fooddelivery.ordersservice.datalayer;

import com.fooddelivery.ordersservice.datalayer.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document(collection = "orders")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    private String id;

    private OrderIdentifier orderIdentifier;
    private RestaurantIdentifier restaurantIdentifier;
    private MenuIdentifier menuIdentifier;
    private ClientIdentifier clientIdentifier;
    private DeliveryDriverIdentifier deliveryDriverIdentifier;

    private String driverFirstName;
    private String driverLastName;
    private String clientUsername;
    private String clientEmail;
    private Double totalPrice;
    private OrderStatus orderStatus;
    private List<Items> items;
    private LocalDate orderDate;



}
