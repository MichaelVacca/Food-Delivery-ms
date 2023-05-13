package com.fooddelivery.ordersservice.businesslayer;

import com.fooddelivery.ordersservice.dataMappingLayer.OrderResponseModelMapper;
import com.fooddelivery.ordersservice.datalayer.*;
import com.fooddelivery.ordersservice.domainClientLayer.Restsauarant.MenuRequestModel;
import com.fooddelivery.ordersservice.domainClientLayer.Restsauarant.MenuResponseModel;
import com.fooddelivery.ordersservice.domainClientLayer.Restsauarant.OrderStatus;
import com.fooddelivery.ordersservice.domainClientLayer.Restsauarant.RestaurantServiceClient;
import com.fooddelivery.ordersservice.domainClientLayer.client.ClientResponseModel;
import com.fooddelivery.ordersservice.domainClientLayer.client.ClientServiceClient;
import com.fooddelivery.ordersservice.domainClientLayer.deliveryDriver.DeliveryDriverResponseModel;
import com.fooddelivery.ordersservice.domainClientLayer.deliveryDriver.DeliveryDriverServiceClient;
import com.fooddelivery.ordersservice.presentationlayer.OrderRequestModel;
import com.fooddelivery.ordersservice.presentationlayer.OrderResponseModel;
import com.fooddelivery.ordersservice.utils.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrdersRepository ordersRepository;
    private final OrderResponseModelMapper orderResponseModelMapper;
    private final ClientServiceClient clientServiceClient;
    private final DeliveryDriverServiceClient deliveryDriverServiceClient;
    private final RestaurantServiceClient restaurantServiceClient;

    @Override
    public List<OrderResponseModel> getAllOrders() {
        List<Order> orders = ordersRepository.findAll();
        return orderResponseModelMapper.entityToResponseModelList(orders);
    }

    @Override
    public OrderResponseModel processClientOrders(OrderRequestModel orderRequestModel, String clientId) {
        ClientResponseModel clientResponseModel = clientServiceClient.getClient(clientId);
        if(clientResponseModel == null){
             throw new NotFoundException("Client not found with id : " + clientId);
        }

        log.info("Driver ID from orderRequestModel: " + orderRequestModel.getDeliveryDriverId());
        DeliveryDriverResponseModel deliveryDriverResponseModel = deliveryDriverServiceClient.getDeliveryDrivers(orderRequestModel.getDeliveryDriverId());
        if(deliveryDriverResponseModel == null){
            throw new NotFoundException("Delivery driver not found with id : " + orderRequestModel.getDeliveryDriverId());
        }

        MenuResponseModel menuResponseModel = restaurantServiceClient.getMenuByMenuId(orderRequestModel.getRestaurantId(), orderRequestModel.getMenuId());
        if(menuResponseModel == null){
            throw new NotFoundException("Menu not found with id : " + orderRequestModel.getMenuId());
        }

        //build
        Order order = Order.builder()
                .orderIdentifier(new OrderIdentifier())
                .restaurantIdentifier(new RestaurantIdentifier(menuResponseModel.getRestaurantId()))
                .menuIdentifier(new MenuIdentifier(menuResponseModel.getMenuId()))
                .clientIdentifier(new ClientIdentifier(clientResponseModel.getClientId()))
                .deliveryDriverIdentifier(new DeliveryDriverIdentifier(deliveryDriverResponseModel.getDeliveryDriverId()))
                .driverFirstName(deliveryDriverResponseModel.getFirstName())
                .driverLastName(deliveryDriverResponseModel.getLastName())
                .clientUsername(clientResponseModel.getUserName())
                .clientEmail(clientResponseModel.getEmailAddress())
                .totalPrice(orderRequestModel.getTotalPrice())
                .orderStatus(orderRequestModel.getOrderStatus())
                .items(orderRequestModel.getItems())
                .orderDate(orderRequestModel.getOrderDate())
                .build();


        Order saved = ordersRepository.save(order);

        OrderStatus orderStatus = OrderStatus.AWAITING_PICKUP;
                switch(saved.getOrderStatus()){
                    case PROCCESSING_ORDER, MAKING_ORDER -> orderStatus = OrderStatus.MAKING;
                    case FOOD_READY -> orderStatus = OrderStatus.AWAITING_PICKUP;
                    case ORDER_COMPLETED -> orderStatus = OrderStatus.IN_TRANSIT;
                    case DELIVERY_COMPLETED -> orderStatus = OrderStatus.DELIVERED;
                    case ORDER_CANCELLED -> orderStatus = OrderStatus.REFUNDED;
                }

        MenuRequestModel menuRequestModel = MenuRequestModel.builder()
                .menuId(menuResponseModel.getMenuId())
                .restaurantId(menuResponseModel.getRestaurantId())
                .typeOfMenu(menuResponseModel.getTypeOfMenu())
                .items(menuResponseModel.getItems())
                .totalPrice(menuResponseModel.getTotalPrice())
                .build();

                restaurantServiceClient.modifyMenuInRestaurant(menuResponseModel.getRestaurantId(), menuRequestModel.getMenuId(),menuRequestModel);
                return orderResponseModelMapper.entityToResponseModel(saved);

    }


}
