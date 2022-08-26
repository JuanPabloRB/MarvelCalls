package com.albo.challenge.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "comics")
public class Comic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long comicId;

    private String name;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "heroes_comics",
            joinColumns = @JoinColumn(name = "comicId", nullable = false, updatable = false),
            inverseJoinColumns = @JoinColumn(name = "heroId", nullable = false, updatable = false))
    private Set<Hero> heroes = new HashSet<>(0);

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "comics_colaborator",
            joinColumns = @JoinColumn(name = "comicId", nullable = false, updatable = false),
            inverseJoinColumns = @JoinColumn(name = "colaboratorId", nullable = false, updatable = false))
    private Set<Colaborator> colaborators = new HashSet<>(0);

    public Comic(String name) {
        this.name = name;
    }

    public Long getComicId() {
        return comicId;
    }

    public void setComicId(Long id) {
        this.comicId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Hero> getHeroes() {
        return heroes;
    }

    public void setHeroes(Set<Hero> heroes) {
        this.heroes = heroes;
    }

    public Set<Colaborator> getColaborators() {
        return colaborators;
    }

    public void setColaborators(Set<Colaborator> colaborators) {
        this.colaborators = colaborators;
    }

    public void addHero(Hero hero) {
        if (!heroes.contains(hero)) {
            heroes.add(hero);

            hero.addComic(this);
        }
    }

    public void removeHero(Hero hero) {
        if (heroes.contains(hero)) {
            heroes.remove(hero);

            hero.removeComic(this);
        }
    }

    public void addColaborator(Colaborator colaborator) {
        if (!colaborators.contains(colaborator)) {
            colaborators.add(colaborator);

            colaborator.addComic(this);
        }
    }

    public void removeColaborator(Colaborator colaborator) {
        if (colaborators.contains(colaborator)) {
            colaborators.remove(colaborator);

            colaborator.removeComic(this);
        }
    }
}
