package ru.yandex.yandexlavka.service;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.yandex.yandexlavka.exception.NotFoundException;
import ru.yandex.yandexlavka.schemas.*;
import ru.yandex.yandexlavka.store.entities.*;
import ru.yandex.yandexlavka.store.repositories.*;
import ru.yandex.yandexlavka.utils.Converter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Component
public class CourierService {
    @Autowired
    CourierRepository courierRepository;
    @Autowired
    RegionsRepository regionsRepository;
    @Autowired
    WorkingHoursRepository workingHoursRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    DeliveryHoursRepositoy deliveryHoursRepositoy;
    @Autowired
    OrderStateEntityRepository orderStateEntityRepository;

    public List<CourierEntity> getAllCouriers() {
        List<CourierEntity> couriers = courierRepository.findAll();
        return couriers;
    }

    public GetCouriersResponse getCouriersResponse(int limit, int offset) {
        List<CourierEntity> courierEntity = getAllCouriers();
        if (courierEntity == null) throw new NotFoundException("Not found.");
        if (courierEntity.size() > limit) {
            limit = courierEntity.size() - 1;
        }
        if (courierEntity.size() < offset) {
            offset = courierEntity.size() - 2;
        }
        List<CourierDto> couriers = new ArrayList<>();
        //TODO postgree может лимит и офсет тащить ?
        for (int i = offset; i < limit; i++) {
            CourierEntity courierFromEntity = courierEntity.get(i);
            int courier = courierFromEntity.getCourier();
            List<Region> regions = regionsRepository.findAllByCourier(courier);
            List<WorkingHoursEntity> hours = workingHoursRepository.findAllByCourier(courier);
            CourierDto courierDto = Converter.toCourierDtoFromCourierEntity(courierFromEntity, regions, hours);
            couriers.add(courierDto);
        }

        GetCouriersResponse getCouriersResponse = new GetCouriersResponse(couriers, limit, offset);
        return getCouriersResponse;
    }

    public CourierDto getCourierById(Integer courier) {
        CourierEntity courierFromEntity = courierRepository.findByCourier(courier);
        if (courierFromEntity == null) throw new NotFoundException("Not found.");
        List<Region> regions = regionsRepository.findAllByCourier(courier);
        List<WorkingHoursEntity> hours = workingHoursRepository.findAllByCourier(courier);

        CourierDto courierDto = Converter.toCourierDtoFromCourierEntity(courierFromEntity, regions, hours);
        return courierDto;
    }

    public OrderDto getOrderById(int orderID) {
        OrderEntity orderFromEntity = orderRepository.findByOrderID(orderID);
        if (orderFromEntity == null) throw new NotFoundException("Not found order.");
        List<String> hours = deliveryHoursRepositoy.findAllByOrderID(orderID).
                stream().map(DeliveryHoursEntity::getDelivery_hours).collect(Collectors.toList());

        OrderDto orderDto = Converter.toOrderDtoFromOrderEntity(orderFromEntity, hours);
        return orderDto;
    }

    public List<CourierDto> saveCreateCourierRequest(CreateCourierRequest request) {
        List<CreateCourierDto> couriersForSave = request.getCouriers();
        List<CourierDto> couriersDTO = new ArrayList<>();
        for (CreateCourierDto item : couriersForSave) {
            CourierEntity courierEntity = Converter.toCourierEntityFromCreateCourierDto(item);
            CourierEntity courierEntityAfterSave = courierRepository.save(courierEntity);
            List<Integer> regions = item.getRegions();
            List<String> workingHours = item.getWorking_hours();
            int courier = courierEntityAfterSave.getCourier();

            for (Integer region : regions) {
                Region regionForSave = new Region(courier, region);
                regionsRepository.save(regionForSave);
            }
            for (String hours : workingHours) {
                WorkingHoursEntity workingHoursSave = new WorkingHoursEntity(courier, hours);
                workingHoursRepository.save(workingHoursSave);
            }
            CourierDto courierDto = new CourierDto(courier, item.getCourier_type_Enum(), regions, workingHours);
            couriersDTO.add(courierDto);
        }
        return couriersDTO;
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


    public CreatOrderResponse getCreatOrderResponse(int limit, int offset) {
        List<OrderEntity> orders = orderRepository.findAll();
        List<OrderDto> ordersDTO = new ArrayList<>();
        if (orders == null || orders.size() == 0) throw new NotFoundException("Not found.");
        if (orders.size() > limit) {
            limit = orders.size() - 1;
        }
        if (orders.size() < offset) {
            offset = orders.size() - 2;
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
        System.out.println(ordersDTO.toString());
        CreatOrderResponse creatOrderResponse = new CreatOrderResponse(ordersDTO);
        return creatOrderResponse;
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
