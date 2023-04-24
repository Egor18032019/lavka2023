package ru.yandex.yandexlavka.schemas;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


public class CreateCouriersResponse {
    private List<CourierDto> couriers;

    public CreateCouriersResponse() {
    }

    public CreateCouriersResponse(List<CourierDto> couriers) {
        this.couriers = couriers;
    }

    public List<CourierDto> getCouriers() {
        return couriers;
    }

    public void setCouriers(List<CourierDto> couriers) {
        this.couriers = couriers;
    }
}
