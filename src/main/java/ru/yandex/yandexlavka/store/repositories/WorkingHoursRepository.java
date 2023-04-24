package ru.yandex.yandexlavka.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yandex.yandexlavka.store.entities.WorkingHoursEntity;

import java.util.List;

public interface WorkingHoursRepository   extends JpaRepository<WorkingHoursEntity, Integer> {
    List<WorkingHoursEntity> findAllByCourier(int courier) ;
 }
