package com.albo.challenge.repository;

import com.albo.challenge.model.MainHero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MainHeroesRepository extends JpaRepository<MainHero, Long> {

    // Obtiene un personaje por el nombre.
    Optional<MainHero> findByName(String name);
}
