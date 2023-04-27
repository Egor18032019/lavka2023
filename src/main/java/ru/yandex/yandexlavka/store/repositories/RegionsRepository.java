package ru.yandex.yandexlavka.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yandex.yandexlavka.store.entities.Region;

import java.util.List;

public interface RegionsRepository extends JpaRepository<Region, Integer> {
    List<Region> findAllByCourier(int courier);

}
