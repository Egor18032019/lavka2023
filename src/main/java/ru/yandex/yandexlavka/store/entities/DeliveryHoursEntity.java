package ru.yandex.yandexlavka.store.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "delivery_hours")
public class DeliveryHoursEntity {
    @Id
    @Column(name = "order_id")
    Integer orderID;
    @Column()
    String delivery_hours;

    public DeliveryHoursEntity() {
    }

    public DeliveryHoursEntity(Integer orderID, String delivery_hours) {
        this.orderID = orderID;
        this.delivery_hours = delivery_hours;
    }

    public Integer getOrderID() {
        return orderID;
    }

    public void setOrderID(Integer orderID) {
        this.orderID = orderID;
    }

    public String getDelivery_hours() {
        return delivery_hours;
    }

    public void setDelivery_hours(String delivery_hours) {
        this.delivery_hours = delivery_hours;
    }
}
