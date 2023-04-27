package ru.yandex.yandexlavka.schemas;

import lombok.Data;
import lombok.ToString;
import ru.yandex.yandexlavka.utils.CourierType;

import java.util.List;

@Data
@ToString
public class CourierDto {
    private Integer courier_id;
    private Enum<CourierType> courier_type;
    private List<Integer> regions;
    private List<String> working_hours;

    public CourierDto(Integer courier_id, Enum<CourierType> courier_type, List<Integer> regions, List<String> working_hours) {
        this.courier_id = courier_id;
        this.courier_type = courier_type;
        this.regions = regions;
        this.working_hours = working_hours;
    }

    public CourierDto() {
    }

    public void setCourier_id(Integer courier_id) {
        this.courier_id = courier_id;
    }

    public void setCourier_type(Enum<CourierType> courier_type) {
        this.courier_type = courier_type;
    }

    public void setRegions(List<Integer> regions) {
        this.regions = regions;
    }

    public void setWorking_hours(List<String> working_hours) {
        this.working_hours = working_hours;
    }

    public Integer getCourier_id() {
        return courier_id;
    }

    public String getCourier_type() {
        return courier_type.toString();
    }

    public List<Integer> getRegions() {
        return regions;
    }

    public List<String> getWorking_hours() {
        return working_hours;
    }
}
/*

 */