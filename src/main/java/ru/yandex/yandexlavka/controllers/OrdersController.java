package ru.yandex.yandexlavka.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.yandexlavka.exception.BadRequestException;
import ru.yandex.yandexlavka.schemas.CompleteOrderRequestDto;
import ru.yandex.yandexlavka.schemas.CreatOrderResponse;
import ru.yandex.yandexlavka.schemas.CreateOrderRequest;
import ru.yandex.yandexlavka.schemas.OrderDto;
import ru.yandex.yandexlavka.service.CourierService;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
public class OrdersController {
    @Autowired
    CourierService courierService;

    @PostMapping(value = "/orders")
    public CreatOrderResponse createOrder(@RequestBody() CreateOrderRequest request) {
        System.out.println(request.toString());
        List<OrderDto> orders = courierService.saveCreateOrderRequest(request);
        System.out.println(orders.toString());
        CreatOrderResponse creatOrderResponse = new CreatOrderResponse(orders);
        return creatOrderResponse;
    }

    @PostMapping(value = "/orders/complete")
    public CompleteOrderRequestDto completeOrder(@RequestBody() CompleteOrderRequestDto request) {
        courierService.saveCompleteOrderRequestDto(request);
        return request;
    }

    @GetMapping(value = "/orders/{order_id}")
    public OrderDto getOrder(
            @PathVariable(value = "order_id", required = true) String order_id
    ) {
        int orderID;
        try {
            orderID = Integer.parseInt(order_id);
        } catch (NumberFormatException nfe) {
            throw new BadRequestException("The order_id must be an integer.");
        }

        OrderDto orderDto = courierService.getOrderById(orderID);

        return orderDto;
    }

    @GetMapping(value = "/orders")
    public CreatOrderResponse getOrders(
            @RequestParam(value = "limit", required = false) Optional<Integer> limit,
            @RequestParam(value = "offset", required = false) Optional<Integer> offset) {
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

        //TODO Postgres limim and offset use !!
        CreatOrderResponse greatOrderResponse = courierService.getCreatOrderResponse(realLimit, realOffset);
        return greatOrderResponse;

    }
}
