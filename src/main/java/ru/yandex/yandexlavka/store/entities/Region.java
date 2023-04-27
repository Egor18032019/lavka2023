package ru.yandex.yandexlavka.store.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.ToString;
import lombok.experimental.FieldDefaults;


@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "regions")
@ToString
public class Region {
    @Id
    @Column(name = "courier_id")
    Integer courier;
    @Column()
    Integer number_region;

    public Region(Integer courier, Integer number_region) {
        this.courier = courier;
        this.number_region = number_region;
    }

    public Region() {
    }

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
