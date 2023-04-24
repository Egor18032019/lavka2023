package ru.yandex.yandexlavka.schemas;

import lombok.Data;
import lombok.ToString;
import ru.yandex.yandexlavka.utils.CourierType;

import java.util.List;

@Data
@ToString
public class CreateCourierDto {

    private Enum<CourierType> courier_type;
    private List<Integer> regions;
    private List<String> working_hours;

    public CreateCourierDto() {
    }

    public CreateCourierDto(Enum<CourierType> courier_type, List<Integer> regions, List<String> working_hours) {
        this.courier_type = courier_type;
        this.regions = regions;
        this.working_hours = working_hours;
    }

    public void setCourier_type(String courier_type) {
        this.courier_type = CourierType.valueOf(courier_type);
    }

    public void setRegions(List<Integer> regions) {
        this.regions = regions;
    }

    public void setWorking_hours(List<String> working_hours) {
        this.working_hours = working_hours;
    }


    public String getCourier_type() {
        return courier_type.toString();
    }
    public Enum<CourierType> getCourier_type_Enum() {
        return courier_type;
    }

    public List<Integer> getRegions() {
        return regions;
    }

    public List<String> getWorking_hours() {
        return working_hours;
    }
}
