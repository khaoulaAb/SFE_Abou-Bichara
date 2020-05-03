package com.example.demo.entities;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

import java.util.Collection;
import java.util.Set;


@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   // @NotNull(message = "email est obligatoire")
    private String email;

    //@NotNull(message = "Mot de passe est obligatoire")
    private String password;

   // @NotNull(message = "Nom est obligatoire")
    private String nom;

  //  @NotNull(message = "Prenom est obligatoire")
    private String prenom;

    private boolean actived;
    @ManyToOne
    @JoinColumn(name="role")
    private Role role;

    public User() {
    }

    public User(String email, String password, boolean actived) {
        this.email = email;
        this.password = password;
        this.actived = actived;


    }

    public User(@NotNull(message = "email est obligatoire") String email, @NotNull(message = "Mot de passe est obligatoire") String password, Role role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User(String email, String password, String nom, String prenom, boolean actived) {
        this.email = email;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.actived = actived;
    }

    public User( @NotNull(message = "email est obligatoire") String email, @NotNull(message = "Mot de passe est obligatoire") String password, @NotNull(message = "Nom est obligatoire") String nom, @NotNull(message = "Prenom est obligatoire") String prenom, boolean actived, Role role) {
        this.email = email;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.actived = actived;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isActived() {
        return actived;
    }

    public void setActived(boolean actived) {
        this.actived = actived;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}
