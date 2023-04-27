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

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Component
public class CourierService {
    CourierRepository courierRepository;
    RegionsRepository regionsRepository;
    WorkingHoursRepository workingHoursRepository;

    public CourierService(CourierRepository courierRepository, RegionsRepository regionsRepository, WorkingHoursRepository workingHoursRepository) {
        this.courierRepository = courierRepository;
        this.regionsRepository = regionsRepository;
        this.workingHoursRepository = workingHoursRepository;
    }

    public List<CourierEntity> getAllCouriers() {
        List<CourierEntity> couriers = courierRepository.findAll();
        return couriers;
    }

    public GetCouriersResponse getCouriersResponse(int limit, int offset) {
        List<CourierEntity> courierEntity = getAllCouriers();
        if (courierEntity == null) throw new NotFoundException("Not found.");
        if (courierEntity.size() < limit) {
            limit = courierEntity.size();
        }
        if (courierEntity.size() < offset) {
            offset = courierEntity.size() - 2;
            if (offset < 0) {
                offset = 0;
            }
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
}
