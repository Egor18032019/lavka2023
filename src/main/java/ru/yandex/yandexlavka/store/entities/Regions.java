package ru.yandex.yandexlavka.store.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;


@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "regions")
@ToString
public class Regions {
    @Id
    @Column(name = "courier")
    Integer courier;
    Integer number_region;

    public Integer getCourier() {
        return courier;
    }

    public Integer getNumberRegion() {
        return number_region;
    }

    public void setCourier(Integer courier) {
        this.courier = courier;
    }

    public void setNumberRegion(Integer numberRegion) {
        this.number_region = numberRegion;
    }
}
