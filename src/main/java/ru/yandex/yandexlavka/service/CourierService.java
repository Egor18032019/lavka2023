package ru.yandex.yandexlavka.service;


import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.yandex.yandexlavka.schemas.CourierDto;
import ru.yandex.yandexlavka.schemas.GetCouriersResponse;
import ru.yandex.yandexlavka.store.entities.CourierEntity;
import ru.yandex.yandexlavka.store.entities.Regions;
import ru.yandex.yandexlavka.store.entities.WorkingHoursEntity;
import ru.yandex.yandexlavka.store.repositories.CourierRepository;
import ru.yandex.yandexlavka.store.repositories.RegionsRepository;
import ru.yandex.yandexlavka.store.repositories.WorkingHoursRepository;
import ru.yandex.yandexlavka.utils.Converter;

import java.util.ArrayList;
import java.util.List;

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



    public List<CourierEntity> getAllCouriers() {
        List<CourierEntity> couriers = courierRepository.findAll();
        return couriers;
    }

    public GetCouriersResponse getCouriersResponse(int limit, int offset) {
        List<CourierEntity> courierEntity = getAllCouriers();
        List<CourierDto> couriers = new ArrayList<>();
        //TODO postgree может лимит и офсет тащить ?
        for (int i = offset; i < limit; i++) {
            CourierEntity courierFromEntity = courierEntity.get(i);
            int courier = courierFromEntity.getCourier();
            List<Regions> regions = regionsRepository.findAllByCourier(courier);
            List<WorkingHoursEntity> hours = workingHoursRepository.findAllByCourier(courier);
            CourierDto courierDto = Converter.toCourierDtoFromCourierEntity(courierFromEntity, regions, hours);
            couriers.add(courierDto);
        }

        GetCouriersResponse getCouriersResponse = new GetCouriersResponse(couriers, limit, offset);
        return getCouriersResponse;
    }

    public CourierDto getCourierById(Integer courier) {
        CourierEntity courierFromEntity = courierRepository.findByCourier(courier);
        List<Regions> regions = regionsRepository.findAllByCourier(courier);
        List<WorkingHoursEntity> hours = workingHoursRepository.findAllByCourier(courier);

        CourierDto courierDto = Converter.toCourierDtoFromCourierEntity(courierFromEntity, regions, hours);
        return courierDto;
    }
}
