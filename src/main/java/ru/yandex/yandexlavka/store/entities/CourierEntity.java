package ru.yandex.yandexlavka.store.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;



@Entity
@Table(name = "courier")
@FieldDefaults(level = AccessLevel.PRIVATE)//все поля приватные
public class CourierEntity {
    public static final int START_SEQ = 10;
    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    Integer courier;
    @Column()
    String courier_type;

    public CourierEntity() {
    }

    public CourierEntity(  String courier_type) {
        this.courier_type = courier_type;
    }

    public CourierEntity(Integer courier, String courier_type) {
        this.courier = courier;
        this.courier_type = courier_type;
    }

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
