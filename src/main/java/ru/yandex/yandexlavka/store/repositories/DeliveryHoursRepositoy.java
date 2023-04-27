package ru.yandex.yandexlavka.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yandex.yandexlavka.store.entities.DeliveryHoursEntity;
import ru.yandex.yandexlavka.store.entities.OrderEntity;
import ru.yandex.yandexlavka.store.entities.WorkingHoursEntity;

import java.util.List;

public interface DeliveryHoursRepositoy extends JpaRepository<DeliveryHoursEntity, Integer> {
    List<DeliveryHoursEntity> findAllByOrderID(int id) ;
}
