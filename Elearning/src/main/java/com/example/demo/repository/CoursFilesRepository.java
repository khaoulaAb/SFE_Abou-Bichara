package com.example.demo.repository;

import com.example.demo.entities.CoursFiles;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CoursFilesRepository extends CrudRepository<CoursFiles, Long> {
   @Query("select f from CoursFiles as f where f.cours.id=?1")
    List<CoursFiles> findFilesbyCoursId(Long coursId);

   @Modifying
   @Query("delete from CoursFiles as f where f.cours.id=?1 and f.modifiedFileName in(?2)")
   void deleteFileByCoursIdAndImageNames(Long id, List<String> removeImages);

   @Modifying
   @Query("delete from CoursFiles as f where f.cours.id=?1")
   void deleteFilesByCoursId(Long coursId);
}
