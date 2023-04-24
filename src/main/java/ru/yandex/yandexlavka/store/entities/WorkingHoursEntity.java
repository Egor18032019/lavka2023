package ru.yandex.yandexlavka.store.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;



@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "working_hours")
public class WorkingHoursEntity {
    @Id
    @Column(name = "courier")
    Integer courier;
    String working_hours;

    public WorkingHoursEntity() {
    }

    public WorkingHoursEntity(Integer courier, String working_hours) {
        this.courier = courier;
        this.working_hours = working_hours;
    }

    public Integer getCourier() {
        return courier;
    }

    public String getWorking_hours() {
        return working_hours;
    }

    public void setCourier(Integer courier) {
        this.courier = courier;
    }

    public void setWorking_hours(String working_hours) {
        this.working_hours = working_hours;
    }
}
