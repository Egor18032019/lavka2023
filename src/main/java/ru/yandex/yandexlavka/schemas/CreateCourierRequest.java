package ru.yandex.yandexlavka.schemas;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class CreateCourierRequest {
    private List<CreateCourierDto> couriers;

    public void setCouriers(List<CreateCourierDto> couriers) {
        this.couriers = couriers;
    }

    public List<CreateCourierDto> getCouriers() {
        return couriers;
    }
}
