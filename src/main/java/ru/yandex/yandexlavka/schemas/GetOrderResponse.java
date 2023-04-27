package ru.yandex.yandexlavka.schemas;

import java.util.List;

public class GetOrderResponse {
    private List<OrderDto> orders;
    private Integer limit  ;
    private Integer offset ;

    public GetOrderResponse() {
    }

    public GetOrderResponse(List<OrderDto> orders, Integer limit, Integer offset) {
        this.orders = orders;
        this.limit = limit;
        this.offset = offset;
    }

    public List<OrderDto> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderDto> orders) {
        this.orders = orders;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }
}
