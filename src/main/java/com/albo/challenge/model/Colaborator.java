package com.albo.challenge.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "colaborators")
public class Colaborator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long colaboratorId;
    private String name;
    private String role;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "colaborators", cascade = {CascadeType.PERSIST})
    private Set<Comic> comics = new HashSet<>(0);

    public Colaborator(String name, String role) {
        this.name = name;
        this.role = role;
    }

    public Long getColaboratorId() {
        return colaboratorId;
    }

    public void setColaboratorId(Long id) {
        this.colaboratorId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

            comic.addColaborator(this);
        }
    }

    public void removeComic(Comic comic) {

        if (comics.contains(comic)) {
            comics.remove(comic);

            comic.removeColaborator(this);
        }
    }
}
