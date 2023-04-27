package ru.yandex.yandexlavka.schemas;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
public class CreateOrderDto {
    private Integer cost;
    private List<String> delivery_hours;
    private Integer regions;
    private Integer weight;

    public CreateOrderDto() {
    }

    public CreateOrderDto(Integer cost, List<String> delivery_hours, Integer regions, Integer weight) {
        this.cost = cost;
        this.delivery_hours = delivery_hours;
        this.regions = regions;
        this.weight = weight;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public void setDelivery_hours(List<String> delivery_hours) {
        this.delivery_hours = delivery_hours;
    }

    public void setRegions(Integer regions) {
        this.regions = regions;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getCost() {
        return cost;
    }

    public List<String> getDelivery_hours() {
        return delivery_hours;
    }

    public Integer getRegions() {
        return regions;
    }

    public Integer getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "CreateOrderDto{" +
                "cost=" + cost +
                ", delivery_hours=" + delivery_hours +
                ", regions=" + regions +
                ", weight=" + weight +
                '}';
    }
}
