package ru.yandex.yandexlavka.schemas;

import lombok.Data;

import java.util.Date;

@Data
public class CompleteOrder {
    private Date complete_time;
    private Integer courier_id;
    private Integer order_id;

    public CompleteOrder() {
    }

    public CompleteOrder(Date complete_time, Integer courier_id, Integer order_id) {
        this.complete_time = complete_time;
        this.courier_id = courier_id;
        this.order_id = order_id;
    }

    public Date getComplete_time() {
        return complete_time;
    }

    public void setComplete_time(Date complete_time) {
        this.complete_time = complete_time;
    }

    public Integer getCourier_id() {
        return courier_id;
    }

    public void setCourier_id(Integer courier_id) {
        this.courier_id = courier_id;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }
}
