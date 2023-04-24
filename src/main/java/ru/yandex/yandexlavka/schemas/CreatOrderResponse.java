package ru.yandex.yandexlavka.schemas;

import java.util.List;

public class CreatOrderResponse {
    private List<OrderDto> orders;

    public CreatOrderResponse() {
    }

    public CreatOrderResponse(List<OrderDto> orders) {
        this.orders = orders;
    }

    public List<OrderDto> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderDto> orders) {
        this.orders = orders;
    }
}
