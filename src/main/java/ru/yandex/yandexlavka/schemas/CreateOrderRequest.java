package ru.yandex.yandexlavka.schemas;

import java.util.List;

public class CreateOrderRequest {
    private List<CreateOrderDto> orders;

    public CreateOrderRequest() {
    }

    public CreateOrderRequest(List<CreateOrderDto> orders) {
        this.orders = orders;
    }

    public void setOrders(List<CreateOrderDto> orders) {
        this.orders = orders;
    }

    public List<CreateOrderDto> getOrders() {
        return orders;
    }

    @Override
    public String toString() {
        return "CreateOrderRequest{" +
                "orders=" + orders.toString() +
                '}';
    }
}
