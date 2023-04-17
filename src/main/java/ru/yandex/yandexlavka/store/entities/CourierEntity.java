package ru.yandex.yandexlavka.store.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;


@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)//все поля приватные
@Entity
@Table(name = "courier")
public class CourierEntity {
    @Id
    Integer courier;
    @Column()
    String courier_type;

    public void setCourier(Integer courier) {
        this.courier = courier;
    }

    public void setCourier_type(String courier_type) {
        this.courier_type = courier_type;
    }

    public Integer getCourier() {
        return courier;
    }

    public String getCourier_type() {
        return courier_type;
    }
}
