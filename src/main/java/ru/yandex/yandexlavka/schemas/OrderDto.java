package ru.yandex.yandexlavka.schemas;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class OrderDto {
    private Integer order_id;
    private Integer cost;
    private Integer regions;
    private Integer weight;
    private List<String> delivery_hours;

    public OrderDto() {
    }

    public OrderDto(Integer order_id, Integer cost, Integer regions, Integer weight, List<String> delivery_hours) {
        this.order_id = order_id;
        this.cost = cost;
        this.regions = regions;
        this.weight = weight;
        this.delivery_hours = delivery_hours;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getRegions() {
        return regions;
    }

    public void setRegions(Integer regions) {
        this.regions = regions;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public List<String> getDelivery_hours() {
        return delivery_hours;
    }

    public void setDelivery_hours(List<String> delivery_hours) {
        this.delivery_hours = delivery_hours;
    }
}
