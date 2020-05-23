package com.example.demo.repository;

import com.example.demo.entities.Remarque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface RemarqueRepository extends JpaRepository<Remarque,Long> {
    @Modifying
    @Query("delete from Remarque as r where r.cours.id=?1")
    void deleteByCoursId(Long coursId);
}
