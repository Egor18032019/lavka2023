package ru.yandex.yandexlavka.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yandex.yandexlavka.store.entities.CourierEntity;
import ru.yandex.yandexlavka.store.entities.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {
    OrderEntity  findByOrderID(int  Id);
}
