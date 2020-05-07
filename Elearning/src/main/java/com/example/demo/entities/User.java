package com.example.demo.entities;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

import java.util.Collection;
import java.util.Set;


@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email(message="Email Adress")
    @Column(unique = true)
    @NotNull(message = "email est obligatoire")
    private String email;

    @NotNull(message = "Mot de passe est obligatoire")
    @Size(min=10)
    private String password;

    @NotNull(message = "Nom est obligatoire")
    private String nom;

    @NotNull(message = "Prenom est obligatoire")
    private String prenom;

    @NotNull(message = "genre est obligatoire")
    private String genre;

    private boolean actived;

    @ManyToOne
    @JoinColumn(name="role")
    private Role role;


    public User() {
    }

    public User(@Email(message = "Email Adress") @NotNull(message = "email est obligatoire") String email, @NotNull(message = "Mot de passe est obligatoire") @Size(min = 10) String password, @NotNull(message = "Nom est obligatoire") String nom, @NotNull(message = "Prenom est obligatoire") String prenom, @NotNull(message = "genre est obligatoire") String genre, boolean actived) {
        this.email = email;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.genre = genre;
        this.actived = actived;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public boolean isActived() {
        return actived;
    }

    public void setActived(boolean actived) {
        this.actived = actived;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
