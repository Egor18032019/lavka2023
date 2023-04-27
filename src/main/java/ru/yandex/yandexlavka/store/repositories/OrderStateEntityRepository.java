package ru.yandex.yandexlavka.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yandex.yandexlavka.store.entities.OrderStateEntity;

import java.util.List;

public interface OrderStateEntityRepository extends JpaRepository<OrderStateEntity, Integer> {
    List<OrderStateEntity> findAllByOrderID(int id);
}
