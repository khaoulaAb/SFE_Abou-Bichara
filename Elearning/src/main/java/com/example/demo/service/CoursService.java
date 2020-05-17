package com.example.demo.service;

import com.example.demo.entities.Cours;
import com.example.demo.entities.CoursFiles;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface CoursService {
    List<Cours> getAllCours();
    List<Cours> getCoursbyUser(HttpServletRequest httpServletRequest);
    Cours saveCours(Cours cours, HttpServletRequest httpServletRequest);
    Cours findById(Long id);

    List<CoursFiles> findFilesbyCoursId(Long coursId);

    Cours updateCours(Cours cours, HttpServletRequest httpServletRequest);

    void deleteFilesByCoursId(Long coursId);

    void deleteCours(Long coursId);
}
