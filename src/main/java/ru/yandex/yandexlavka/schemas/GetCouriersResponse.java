package ru.yandex.yandexlavka.schemas;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


public class GetCouriersResponse {
    private List<CourierDto> couriers;
    private Integer limit  ;
    private Integer offset ;

    public GetCouriersResponse(List<CourierDto> couriers, Integer limit, Integer offset) {
        this.couriers = couriers;
        this.limit = limit;
        this.offset = offset;
    }

    public GetCouriersResponse() {
    }

    public Integer getLimit() {
        return limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public List<CourierDto> getCouriers() {
        return couriers;
    }

    public void setCouriers(List<CourierDto> couriers) {
        this.couriers = couriers;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }
}
