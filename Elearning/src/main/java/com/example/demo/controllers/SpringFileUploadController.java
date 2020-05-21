package com.example.demo.controllers;

import com.example.demo.entities.Cours;
import com.example.demo.entities.CoursFiles;
import com.example.demo.entities.Filiere;
import com.example.demo.repository.CoursRepository;
import com.example.demo.repository.FiliereRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.CoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class SpringFileUploadController {
   @Autowired
   private CoursService coursService;

   @Autowired
   private CoursRepository coursRepository;

   @Autowired
   private FiliereRepository filiereRepository;

   @GetMapping("/cours")
   public String cours(Model model){
       List<Cours> Listcours = coursService.getAllCours();
       model.addAttribute("cours", new Cours());
       model.addAttribute("coursfiles",new ArrayList<CoursFiles>());
       // model.addAttribute("isAdd",true);

       List<Filiere> filieres= filiereRepository.findAll();
       model.addAttribute("filieres",filieres);
       model.addAttribute("Listcours",Listcours);

        return "cours";
    }


    @PostMapping("/cours/save")
    public String saveCours(@ModelAttribute Cours cours, RedirectAttributes redirectAttributes, Model model, HttpServletRequest httpServletRequest){
       Cours newCours = coursService.saveCours(cours,httpServletRequest);

       if(newCours!=null){
           redirectAttributes.addFlashAttribute("successMessage","cours est enregistré");
           return "redirect:/cours";
       }
       else{
           redirectAttributes.addFlashAttribute("errormessage","cours n'est pas enregistré");
            model.addAttribute("cours",cours);
            return "cours";
       }
    }

    @GetMapping("/cours/editcours/{coursId}")
    public String editCours(@PathVariable Long coursId, Model model){

        Cours newcours = coursService.findById(coursId);
        List<CoursFiles> coursFiles= coursService.findFilesbyCoursId(coursId);

        model.addAttribute("cours", newcours);
        model.addAttribute("coursfiles",coursFiles);
        List<Filiere> filieres= filiereRepository.findAll();
        model.addAttribute("filieres",filieres);
        return "updateCours";
    }



    @PostMapping("/cours/update")
    public String updateCours(@ModelAttribute Cours cours, RedirectAttributes redirectAttributes, Model model, HttpServletRequest httpServletRequest){
        Cours newCours = coursService.updateCours(cours,httpServletRequest);

        if(newCours!=null){
            redirectAttributes.addFlashAttribute("successMessage","cours est modifié");
            return "redirect:/cours";
        }
        else{
            redirectAttributes.addFlashAttribute("errormessage","cours n'est pas modifié");
            model.addAttribute("cours",cours);
            return "cours";
        }
    }



    @GetMapping("/cours/deletecours/{coursId}")
    public String deletecours(@PathVariable Long coursId, RedirectAttributes redirectAttributes){
        coursService.deleteFilesByCoursId(coursId);
        coursService.deleteCours(coursId);
        redirectAttributes.addFlashAttribute("successmessage","cours est supprimé");

        return "redirect:/cours";
    }


    @GetMapping("/cours/coursDetails/{coursId}")
    public String CoursDetails(@PathVariable Long coursId, Model model){

        Cours cours = coursService.findById(coursId);
        List<CoursFiles> coursFiles= coursService.findFilesbyCoursId(coursId);

        model.addAttribute("cours", cours);
        model.addAttribute("coursfiles",coursFiles);
        List<Filiere> filieres= filiereRepository.findAll();
        model.addAttribute("filieres",filieres);
        return "coursDetails";
    }


}
