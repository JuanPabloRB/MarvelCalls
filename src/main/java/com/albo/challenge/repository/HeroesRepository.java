package com.albo.challenge.repository;

import com.albo.challenge.model.Hero;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HeroesRepository extends CrudRepository<Hero, Long> {
    // Obtiene un personaje por el nombre completo.
    Optional<Hero> findByFullName(String fullName);
}
