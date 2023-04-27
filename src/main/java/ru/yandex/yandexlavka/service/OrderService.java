package ru.yandex.yandexlavka.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.yandex.yandexlavka.exception.NotFoundException;
import ru.yandex.yandexlavka.schemas.*;
import ru.yandex.yandexlavka.store.entities.DeliveryHoursEntity;
import ru.yandex.yandexlavka.store.entities.OrderEntity;
import ru.yandex.yandexlavka.store.entities.OrderStateEntity;
import ru.yandex.yandexlavka.store.repositories.DeliveryHoursRepositoy;
import ru.yandex.yandexlavka.store.repositories.OrderRepository;
import ru.yandex.yandexlavka.store.repositories.OrderStateEntityRepository;
import ru.yandex.yandexlavka.utils.Converter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Component
public class OrderService {
    OrderRepository orderRepository;
    OrderStateEntityRepository orderStateEntityRepository;

    DeliveryHoursRepositoy deliveryHoursRepositoy;

    public OrderService(OrderRepository orderRepository, OrderStateEntityRepository orderStateEntityRepository, DeliveryHoursRepositoy deliveryHoursRepositoy) {
        this.orderRepository = orderRepository;
        this.orderStateEntityRepository = orderStateEntityRepository;
        this.deliveryHoursRepositoy = deliveryHoursRepositoy;
    }

    public OrderDto getOrderById(int orderID) {
        OrderEntity orderFromEntity = orderRepository.findByOrderID(orderID);
        if (orderFromEntity == null) throw new NotFoundException("Not found order.");
        List<String> hours = deliveryHoursRepositoy.findAllByOrderID(orderID).
                stream().map(DeliveryHoursEntity::getDelivery_hours).collect(Collectors.toList());

        OrderDto orderDto = Converter.toOrderDtoFromOrderEntity(orderFromEntity, hours);
        return orderDto;
    }

    public List<OrderDto> saveCreateOrderRequest(CreateOrderRequest request) {
        List<CreateOrderDto> orders = request.getOrders();
        List<OrderDto> ordersDTO = new ArrayList<>();
        for (CreateOrderDto item : orders) {
            OrderEntity orderEntity = orderRepository.save(Converter.toOrderEntityFromCreateOrderRequest(item));
            List<String> deliveryHours = item.getDelivery_hours();
            int orderId = orderEntity.getOrderID();
            for (String hours : deliveryHours) {
                DeliveryHoursEntity deliveryHoursSave = new DeliveryHoursEntity(orderId, hours);
                deliveryHoursRepositoy.save(deliveryHoursSave);
            }

            OrderStateEntity orderStateEntity = new OrderStateEntity(orderId);
            orderStateEntityRepository.save(orderStateEntity);
            OrderDto orderDTO = new OrderDto(orderId, item.getCost(), item.getRegions(), item.getWeight(), deliveryHours);
            ordersDTO.add(orderDTO);
        }
        return ordersDTO;
    }

    public GetOrderResponse getCreatOrderResponse(int limit, int offset) {
        List<OrderEntity> orders = orderRepository.findAll();
        List<OrderDto> ordersDTO = new ArrayList<>();
        if (orders == null) throw new NotFoundException("Not found.");
        if (orders.size() < limit) {
            limit = orders.size();
        }
        if (orders.size() < offset) {
            offset = orders.size() - 2;
            if (offset < 0) {
                offset = 0;
            }
        }
        //TODO postgree может limit и offset тащить ?
        for (int i = offset; i < limit; i++) {
            OrderEntity item = orders.get(i);
            List<String> deliveryHours = deliveryHoursRepositoy.findAllByOrderID(item.getOrderID())
                    .stream()
                    .map(DeliveryHoursEntity::getDelivery_hours)
                    .collect(Collectors.toList());

            OrderDto orderDTO = new OrderDto(item.getOrderID(), item.getCost(), item.getRegions(), item.getWeight(), deliveryHours);
            ordersDTO.add(orderDTO);

        }
        GetOrderResponse gettOrderResponse = new GetOrderResponse(ordersDTO, limit, offset);
        return gettOrderResponse;
    }
    public void saveCompleteOrderRequestDto(CompleteOrderRequestDto request) {
        List<CompleteOrder> orders = request.getComplete_info();
        for (CompleteOrder item : orders) {
            //TODO проверка есть ли такой курьер в базе
//TODO был назначен на другого курьера или не назначен совсем — следует вернуть ошибку `HTTP 400 Bad Request`.
            if (orderStateEntityRepository.findAllByOrderID(item.getOrder_id()).size() == 0) {
                throw new NotFoundException("Not found order");
            }
            OrderStateEntity orderStateEntity = new OrderStateEntity(item.getOrder_id(), item.getCourier_id(), true, item.getComplete_time());
            orderStateEntityRepository.save(orderStateEntity);
        }
    }
}
