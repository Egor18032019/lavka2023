package ru.yandex.yandexlavka.store.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.Date;

@Entity
@Table(name = "status")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderStateEntity {

    @Id
    @Column(name="order_id")
    Integer orderID;
    @Column(name="courier_id")
    Integer courierID;
    @Column()
    Instant createdAt = Instant.now();
    @Column()
    Boolean isComplete = false;
    @Column()
    Date dateComplete;

    public OrderStateEntity() {
    }

    public OrderStateEntity(Integer orderID) {
        this.orderID = orderID;

    }

    public OrderStateEntity(Integer orderID, Integer courierID, Boolean isComplete, Date dateComplete) {
        this.orderID = orderID;
        this.courierID = courierID;

        this.isComplete = isComplete;
        this.dateComplete = dateComplete;
    }

    public OrderStateEntity(Integer orderID, Integer courierID, Instant createdAt, Boolean isComplete, Date dateComplete) {
        this.orderID = orderID;
        this.courierID = courierID;
        this.createdAt = createdAt;
        this.isComplete = isComplete;
        this.dateComplete = dateComplete;
    }
}
