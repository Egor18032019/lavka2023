package ru.yandex.yandexlavka.store.entities;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Entity
@Table(name = "orders")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderEntity implements Serializable{

    private static final long serialVersionUID = -7049957706738879274L;

    @Id
    @SequenceGenerator(name = "orders_seq", sequenceName = "orders_seq", allocationSize = 1 )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    @Column(name="order_id")
    Integer orderID;
    @Column()
    Integer cost;
    @Column()
    Integer regions;
    @Column()
    Integer weight;

    public OrderEntity() {
    }

    public OrderEntity(Integer cost, Integer regions, Integer weight) {
        this.cost = cost;
        this.regions = regions;
        this.weight = weight;
    }

    public OrderEntity(Integer orderID, Integer cost, Integer regions, Integer weight) {
        this.orderID = orderID;
        this.cost = cost;
        this.regions = regions;
        this.weight = weight;
    }

    public void setOrderID(Integer order_id) {
        this.orderID = order_id;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public void setRegions(Integer regions) {
        this.regions = regions;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getOrderID() {
        return orderID;
    }

    public Integer getCost() {
        return cost;
    }

    public Integer getRegions() {
        return regions;
    }

    public Integer getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "orderID=" + orderID +
                ", cost=" + cost +
                ", regions=" + regions +
                ", weight=" + weight +
                '}';
    }
}
