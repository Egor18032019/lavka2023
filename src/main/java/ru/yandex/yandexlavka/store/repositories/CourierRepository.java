package ru.yandex.yandexlavka.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yandex.yandexlavka.schemas.CreateCourierDto;
import ru.yandex.yandexlavka.store.entities.CourierEntity;

import java.util.List;


public interface CourierRepository extends JpaRepository<CourierEntity, Integer> {
    //    Optional<CourierEntity> findByCourier_id(Integer courier_id);
    List<CourierEntity> findAll();


    CourierEntity findByCourier(int courierId);
}
