package com.example.demo.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;

@Entity
public class Filiere implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    @NotNull(message = "abreviation est obligatoire")
    private String abr;

    @Column(unique = true)
    @NotNull(message = "nom est obligatoire")
    private String nom;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "filiere",cascade = CascadeType.ALL)
    private Collection<Cours> cours;

    public Filiere() {
    }

    public Filiere(@NotNull(message = "abreviation est obligatoire") String abr, @NotNull(message = "nom est obligatoire") String nom) {
        this.abr = abr;
        this.nom = nom;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Collection<Cours> getCours() {
        return cours;
    }

    public void setCours(Collection<Cours> cours) {
        this.cours = cours;
    }

    public String getAbr() {
        return abr;
    }

    public void setAbr(String abr) {
        this.abr = abr;
    }
}

