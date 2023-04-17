package ru.yandex.yandexlavka.utils;

import ru.yandex.yandexlavka.schemas.CourierDto;
import ru.yandex.yandexlavka.store.entities.CourierEntity;
import ru.yandex.yandexlavka.store.entities.Regions;
import ru.yandex.yandexlavka.store.entities.WorkingHoursEntity;

import java.util.ArrayList;
import java.util.List;

public class Converter {
    public static CourierDto toCourierDtoFromCourierEntity(CourierEntity item, List<Regions> regions, List<WorkingHoursEntity> workingHours) {
        List<Integer> regionsForDTO = new ArrayList<>();
        List<String> workingHoursForDTO = new ArrayList<>();
        for (Regions region : regions) {
            regionsForDTO.add(region.getNumberRegion());
        }
        for (WorkingHoursEntity workingHour : workingHours) {
            workingHoursForDTO.add(workingHour.getWorking_hours());
        }

        CourierDto courierDto = new CourierDto(item.getCourier(), CourierType.valueOf(item.getCourier_type()), regionsForDTO, workingHoursForDTO);
        return courierDto;
    }
}
