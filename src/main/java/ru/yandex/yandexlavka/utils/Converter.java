package ru.yandex.yandexlavka.utils;

import ru.yandex.yandexlavka.schemas.CourierDto;
import ru.yandex.yandexlavka.schemas.CreateCourierDto;
import ru.yandex.yandexlavka.schemas.CreateOrderDto;
import ru.yandex.yandexlavka.schemas.OrderDto;
import ru.yandex.yandexlavka.store.entities.*;

import java.util.ArrayList;
import java.util.List;

public class Converter {
    public static CourierDto toCourierDtoFromCourierEntity(CourierEntity item, List<Region> regions, List<WorkingHoursEntity> workingHours) {
        List<Integer> regionsForDTO = new ArrayList<>();
        List<String> workingHoursForDTO = new ArrayList<>();
        for (Region region : regions) {
            regionsForDTO.add(region.getNumberRegion());
        }
        for (WorkingHoursEntity workingHour : workingHours) {
            workingHoursForDTO.add(workingHour.getWorking_hours());
        }

        CourierDto courierDto = new CourierDto(item.getCourier(), CourierType.valueOf(item.getCourier_type()), regionsForDTO, workingHoursForDTO);
        return courierDto;
    }

    public static CourierEntity toCourierEntityFromCreateCourierDto(CreateCourierDto item) {

        CourierEntity courierEntity = new CourierEntity(item.getCourier_type());
        return courierEntity;
    }

    public static OrderEntity toOrderEntityFromCreateOrderRequest(CreateOrderDto item) {

        OrderEntity orderEntity = new OrderEntity(item.getCost(), item.getRegions(), item.getWeight());
        return orderEntity;
    }

    public static OrderDto toOrderDtoFromOrderEntity(OrderEntity orderFromEntity, List<String> hours) {
        OrderDto orderDto = new OrderDto(orderFromEntity.getOrderID(), orderFromEntity.getCost(), orderFromEntity.getRegions(), orderFromEntity.getWeight(), hours);
        return orderDto;
    }
}
