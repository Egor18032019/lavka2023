package ru.yandex.yandexlavka.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.yandexlavka.exception.BadRequestException;
import ru.yandex.yandexlavka.schemas.*;
import ru.yandex.yandexlavka.service.CourierService;

import java.util.List;
import java.util.Optional;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
public class CourierController {
    CourierService courierService;

    public CourierController(CourierService courierService) {
        this.courierService = courierService;
    }

    @PostMapping(value = "/couriers")
    public CreateCouriersResponse createCourier(@RequestBody() CreateCourierRequest request) {
        System.out.println(request.toString());
        List<CourierDto> couriersDTO = courierService.saveCreateCourierRequest(request);
        CreateCouriersResponse createCouriersResponse = new CreateCouriersResponse(couriersDTO);
        return createCouriersResponse;
    }



    @GetMapping(value = "/couriers")
    public GetCouriersResponse getCouriers(
            @RequestParam(value = "limit", required = false) Optional<Integer> limit,
            @RequestParam(value = "offset", required = false) Optional<Integer> offset) {
        System.out.println(limit + " " + offset);
        int realLimit = 1;
        int realOffset = 0;
        if (limit.isPresent()) {
            realLimit = limit.get();
            if (realLimit < 1) throw new BadRequestException("The limit cannot be less than 1.");
            System.out.println("limit " + limit.get());
        }
        if (offset.isPresent()) {
            realOffset = offset.get();
            if (realOffset < 0) throw new BadRequestException("The offset cannot be less than 0.");
            System.out.println("offset " + offset.get());
        }
        /*
         * `offset` или `limit` не передаются, по умолчанию нужно считать, что `offset = 0`, `limit = 1`;
         */
        GetCouriersResponse getCouriersResponse = courierService.getCouriersResponse(realLimit, realOffset);
//

        return getCouriersResponse;

    }

    @GetMapping(value = "/couriers/{courier_id}")
    public CourierDto getCourierById(
            @PathVariable(value = "courier_id", required = true) String courier_id
    ) {
        int courier;
        try {
            courier = Integer.parseInt(courier_id);
        } catch (NumberFormatException nfe) {
            throw new BadRequestException("The courier_id must be an integer.");
        }

        CourierDto courierDto = courierService.getCourierById(courier);

        return courierDto;
    }
}
