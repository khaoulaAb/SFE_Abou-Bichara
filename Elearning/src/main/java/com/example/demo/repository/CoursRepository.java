package com.example.demo.repository;

import com.example.demo.entities.Cours;
import com.example.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CoursRepository extends CrudRepository<Cours,Long> {
    @Query("select c from Cours as c order by c.createdDate desc")
    List<Cours> findAllByCreatedDate();

    @Query("select c from Cours as c where c.user.id=?1")
    List<Cours> getCoursByUser(Long id);

    @Query("select c from Cours as c where c.filiere.id=?1 order by c.createdDate desc ")
    List<Cours> getCoursByFilere(Long idF);
}
