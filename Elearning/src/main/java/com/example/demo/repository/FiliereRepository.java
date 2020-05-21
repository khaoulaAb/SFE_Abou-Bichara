package com.example.demo.repository;


import com.example.demo.entities.Filiere;
import com.example.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FiliereRepository  extends JpaRepository<Filiere,Long> {

    @Query("select f from Filiere as f order by f.abr asc")
    List<Filiere> findAllByAbr();
}
