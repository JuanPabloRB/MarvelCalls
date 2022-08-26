package com.albo.challenge.service;

import com.albo.challenge.collectors.ColaboratorCollector;
import com.albo.challenge.collectors.RelatedHeroesCollector;
import com.albo.challenge.dto.marvel.ColaboratorsDto;
import com.albo.challenge.dto.marvel.HeroesDto;
import com.albo.challenge.exception.NotFoundHeroException;
import com.albo.challenge.model.Hero;
import com.albo.challenge.model.MainHero;
import com.albo.challenge.repository.HeroesRepository;
import com.albo.challenge.repository.MainHeroesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collector;

@Slf4j
@Service
public class MarvelService {

    private HeroesRepository heroesRepository;
    private MainHeroesRepository mainHeroesRepository;

    public MarvelService(HeroesRepository heroesRepository, MainHeroesRepository mainHeroesRepository) {
        this.heroesRepository = heroesRepository;
        this.mainHeroesRepository = mainHeroesRepository;
    }

    public ColaboratorsDto getColaborators(String name) {
        log.debug("getColaborators().name " + name);

        Optional<MainHero> mainHeroOpt = mainHeroesRepository.findByName(name);
        if (mainHeroOpt.isPresent()) {
            MainHero mainHero = mainHeroOpt.get();


            // Obtenemos los datos del personaje desde la db.
            Optional<Hero> heroOpt = heroesRepository.findByFullName(mainHero.getFullName());
            if (heroOpt.isPresent()) {
                Hero hero = heroOpt.get();
                ColaboratorCollector results = hero.getComics().stream()
                        .collect(Collector.of(
                                ColaboratorCollector::new,
                                ColaboratorCollector::accept,
                                ColaboratorCollector::combine,
                                ColaboratorCollector::finish
                        ));

                // Creamos la salida con los valores de las listas auxiliares.
                return new ColaboratorsDto(
                        mainHero.getLastSync(),
                        results.getEditors(),
                        results.getColorists(),
                        results.getWriters());
            } else {
                throw new NotFoundHeroException();
            }
        } else {
            throw new NotFoundHeroException();
        }
    }

    public HeroesDto getRelatedCharacters(String name) {
        log.debug("getRelatedCharacters().name " + name);

        Optional<MainHero> mainHeroOpt = mainHeroesRepository.findByName(name);
        if (mainHeroOpt.isPresent()) {
            MainHero mainHero = mainHeroOpt.get();


            // Obtenemos los datos del personaje desde la db.
            Optional<Hero> heroOpt = heroesRepository.findByFullName(mainHero.getFullName());
            if (heroOpt.isPresent()) {
                Hero hero = heroOpt.get();
                RelatedHeroesCollector results = hero.getComics().stream()
                        .collect(Collector.of(
                                () -> new RelatedHeroesCollector(mainHero.getFullName()),
                                RelatedHeroesCollector::accept,
                                RelatedHeroesCollector::combine,
                                RelatedHeroesCollector::finish
                        ));

                // Creamos la salida con la ultima sincronizacion y los valores de las listas auxiliares.
                return new HeroesDto(
                        mainHero.getLastSync(),
                        results.getCharacters()
                );
            } else {
                throw new NotFoundHeroException();
            }
        } else {
            throw new NotFoundHeroException();
        }

    }
}
