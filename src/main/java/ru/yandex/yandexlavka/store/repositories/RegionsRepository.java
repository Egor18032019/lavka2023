package ru.yandex.yandexlavka.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.yandex.yandexlavka.store.entities.Regions;

import java.util.List;

public interface RegionsRepository  extends JpaRepository<Regions, Integer> {
    List<Regions> findAllByCourier(int courier) ;

}
