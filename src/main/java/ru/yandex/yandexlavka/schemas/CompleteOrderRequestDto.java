package ru.yandex.yandexlavka.schemas;

import java.util.List;

public class CompleteOrderRequestDto {

    private List<CompleteOrder> complete_info;

    public CompleteOrderRequestDto() {
    }

    public CompleteOrderRequestDto(List<CompleteOrder> complete_info) {
        this.complete_info = complete_info;
    }

    public List<CompleteOrder> getComplete_info() {
        return complete_info;
    }

    public void setComplete_info(List<CompleteOrder> complete_info) {
        this.complete_info = complete_info;
    }

    @Override
    public String toString() {
        return "CompleteOrderRequestDto{" +
                "complete_info=" + complete_info +
                '}';
    }
}
