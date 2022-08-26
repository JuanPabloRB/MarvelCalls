package com.albo.challenge.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "heroes")
public class Hero {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long heroId;
    private String fullName;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "heroes", cascade = {CascadeType.PERSIST})
    private Set<Comic> comics = new HashSet<>(0);

    public Hero(String fullName) {
        this.fullName = fullName;
    }

    public Long getHeroId() {
        return heroId;
    }

    public void setHeroId(Long id) {
        this.heroId = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Set<Comic> getComics() {
        return comics;
    }

    public void setComics(Set<Comic> comics) {
        this.comics = comics;
    }


    public void addComic(Comic comic) {
        if (!comics.contains(comic)) {
            comics.add(comic);

            comic.addHero(this);
        }
    }

    public void removeComic(Comic comic) {
        if (comics.contains(comic)) {
            comics.remove(comic);

            comic.removeHero(this);
        }
    }
}
