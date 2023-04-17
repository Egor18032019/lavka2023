package ru.yandex.yandexlavka.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yandex.yandexlavka.schemas.CourierDto;
import ru.yandex.yandexlavka.schemas.CreateCouriersResponse;
import ru.yandex.yandexlavka.schemas.GetCouriersResponse;
import ru.yandex.yandexlavka.service.CourierService;

import java.util.Optional;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
public class CourierController {
    @Autowired
    CourierService courierService;

    @PostMapping(value = "/couriers")
    public ResponseEntity<CreateCouriersResponse> createCourier(@RequestBody() CreateCouriersResponse request) {
//        List<CourierDto> couriers = request.getCouriers();
//        courierService.saveAllCouriers(couriers);

        return new ResponseEntity<>(request, HttpStatus.OK);

    }

    @GetMapping(value = "/couriers")
    public GetCouriersResponse getCouriers(
            @RequestParam(value = "limit", required = false) Optional<Integer> limit,
            @RequestParam(value = "offset", required = false) Optional<Integer> offset) {
        int realLimit = 1;
        int realOffset = 0;
        if (limit.isPresent()) realLimit = limit.get();
        if (offset.isPresent()) realOffset = offset.get();
        /*
         * `offset` или `limit` не передаются, по умолчанию нужно считать, что `offset = 0`, `limit = 1`;
         */
        GetCouriersResponse getCouriersResponse = courierService.getCouriersResponse(realLimit, realOffset);

        return getCouriersResponse;

    }
    @GetMapping(value = "/couriers/{courier_id}")
    public CourierDto getCourierById(
            @PathVariable(value = "courier_id", required = true) Integer courier_id
    ) {
        //TODO выброс ошибки если не нашел
        CourierDto courierDto = courierService.getCourierById(courier_id);
        return courierDto;
    }
}
