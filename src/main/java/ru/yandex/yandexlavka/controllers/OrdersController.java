package ru.yandex.yandexlavka.controllers;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import ru.yandex.yandexlavka.exception.BadRequestException;
import ru.yandex.yandexlavka.schemas.*;
import ru.yandex.yandexlavka.service.OrderService;

import java.util.List;
import java.util.Optional;


@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RestController
public class OrdersController {
    OrderService orderService;

    public OrdersController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping(value = "/orders")
    public CreatOrderResponse createOrder(@RequestBody() CreateOrderRequest request) {
        System.out.println(request.toString());
        List<OrderDto> orders = orderService.saveCreateOrderRequest(request);
        CreatOrderResponse creatOrderResponse = new CreatOrderResponse(orders);
        return creatOrderResponse;
    }

    @PostMapping(value = "/orders/complete")
    public CompleteOrderRequestDto completeOrder(@RequestBody() CompleteOrderRequestDto request) {
        orderService.saveCompleteOrderRequestDto(request);
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
        OrderDto orderDto = orderService.getOrderById(orderID);
        return orderDto;
    }

    @GetMapping(value = "/orders")
    public GetOrderResponse getOrders(
            @RequestParam(value = "limit", required = false) Optional<Integer> limit,
            @RequestParam(value = "offset", required = false) Optional<Integer> offset) {
        int realLimit = 1;
        int realOffset = 0;
        if (limit.isPresent()) {
            realLimit = limit.get();
            if (realLimit < 1) throw new BadRequestException("The limit cannot be less than 1.");
        }
        if (offset.isPresent()) {
            realOffset = offset.get();
            if (realOffset < 0) throw new BadRequestException("The offset cannot be less than 0.");
        }
        /*
         * `offset` или `limit` не передаются, по умолчанию нужно считать, что `offset = 0`, `limit = 1`;
         */
        //TODO Postgres limim and offset use !!
        GetOrderResponse getOrderResponse = orderService.getCreatOrderResponse(realLimit, realOffset);
        return getOrderResponse;

    }
}
