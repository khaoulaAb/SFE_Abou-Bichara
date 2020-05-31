package com.example.demo.controllers;

import com.example.demo.entities.*;
import com.example.demo.repository.*;
import com.example.demo.service.CoursService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

   @Autowired
   private UserService userService;
   @Autowired
   private RemarqueRepository remarqueRepository;




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
           redirectAttributes.addFlashAttribute("successmessage","cours a été ajouté");
           return "redirect:/votreCours";
       }
       else{
           redirectAttributes.addFlashAttribute("errormessage","cours n'est pas enregistré");
            model.addAttribute("cours",cours);
            return "vosCours";
       }
    }
    @GetMapping("/cours/editcours/{coursId}")
    public String editCours(@PathVariable Long coursId, Model model){

        Cours newcours = coursService.findById(coursId);
        if(newcours!=null){
            List<CoursFiles> coursFiles= coursService.findFilesbyCoursId(coursId);

            model.addAttribute("cours", newcours);
            model.addAttribute("coursfiles",coursFiles);
            List<Filiere> filieres= filiereRepository.findAll();
            model.addAttribute("filieres",filieres);
            return "updateCours";
        }
        else
            return "redirect:/erreur";

    }



    @PostMapping("/cours/update")
    public String updateCours(@ModelAttribute Cours cours, RedirectAttributes redirectAttributes, Model model, HttpServletRequest httpServletRequest){
        Cours newCours = coursService.updateCours(cours,httpServletRequest);

        if(newCours!=null){
            redirectAttributes.addFlashAttribute("successmessage","cours a été modifié");
            return "redirect:/votreCours";
        }
        else{
            redirectAttributes.addFlashAttribute("errormessage","cours n'est pas modifié");
            model.addAttribute("cours",cours);
            return "vosCours";
        }
    }



    @GetMapping("/cours/deletecours/{coursId}")
    public String deletecours(@PathVariable Long coursId, RedirectAttributes redirectAttributes){
        Cours cours =coursService.findById(coursId);
        if(cours!=null){
            coursService.deleteFilesByCoursId(coursId);
            coursService.deleteCours(coursId);
            coursService.deleteRemarquesByCours(coursId);
            redirectAttributes.addFlashAttribute("successmessage","cours a été supprimé");

        }
        else
            return "redirect:/erreur";
            return "redirect:/votreCours";
    }


    @GetMapping("/cours/coursDetails/{coursId}")
    public String CoursDetails(@PathVariable Long coursId, Model model){

        Cours cours = coursRepository.findById(coursId).get();
        List<CoursFiles> coursFiles= coursService.findFilesbyCoursId(coursId);

        model.addAttribute("cours", cours);
        model.addAttribute("coursfiles",coursFiles);
        List<Filiere> filieres= filiereRepository.findAll();
        model.addAttribute("filieres",filieres);
        Collection<Remarque> Listremarque= cours.getRemarques();
        model.addAttribute("nbr",Listremarque.size());

        model.addAttribute("Listremarque",Listremarque);

        return "coursDetails";
    }


    @PostMapping("/cours/coursDetails/{coursId}/save")
    public String saveRemarque(@PathVariable Long coursId,@ModelAttribute Remarque remarque,RedirectAttributes redirectAttributes,HttpServletRequest httpServletRequest){
        Cours cours = coursRepository.findById(coursId).get();
        User u = userService.getUserConnect(httpServletRequest);

        remarque.setCours(cours);
        remarque.setUser(u);
        remarque.setCreatedDate(new Date());
        redirectAttributes.addFlashAttribute("successmessage","remarque a été ajouté");

        remarqueRepository.save(remarque);

        return "redirect:/cours/coursDetails/{coursId}";
    }

    @GetMapping("/cours/coursDetails/{coursId}/delete/{RemId}")
    public String deleteRem(@PathVariable Long RemId, RedirectAttributes redirectAttributes){
       Remarque remarque= coursService.findByIdrem(RemId);
       if(remarque!=null) {
           remarqueRepository.deleteById(RemId);
           redirectAttributes.addFlashAttribute("successmessage", "commentaire a été supprimé");

       }
        else
            return "redirect:/erreur";

        return "redirect:/cours/coursDetails/{coursId}";
    }




    @GetMapping(value="/cours/search")
    public String coursSearch(Model model, String s,HttpServletRequest httpServletRequest) {
        model.addAttribute("Listcours", this.courssSearch(s,httpServletRequest));

        model.addAttribute("cours", new Cours());
        model.addAttribute("coursfiles",new ArrayList<CoursFiles>());
        // model.addAttribute("isAdd",true);

        List<Filiere> filieres= filiereRepository.findAll();
        model.addAttribute("filieres",filieres);
        return "/cours";
    }


    public List<Cours> courssSearch(String s,HttpServletRequest httpServletRequest) {
        List<Cours> all=  coursService.getAllCours();
        ArrayList<Cours> l = new ArrayList<Cours>();
        for(int i=0;i<all.size();i++) {
            if((all.get(i).getTitre().equalsIgnoreCase(s))||(containsIgnoreCase(all.get(i).getTitre(),s))
                    ||(all.get(i).getUser().getNom().equalsIgnoreCase(s))||(containsIgnoreCase(all.get(i).getUser().getNom(),s))
                    ||(all.get(i).getFiliere().getAbr().equalsIgnoreCase(s))||(containsIgnoreCase(all.get(i).getFiliere().getAbr(),s))
                    ||(all.get(i).getUser().getPrenom().equalsIgnoreCase(s))||(containsIgnoreCase(all.get(i).getUser().getPrenom(),s))
                    ||(all.get(i).getFiliere().getNom().equalsIgnoreCase(s))||(containsIgnoreCase(all.get(i).getFiliere().getNom(),s))

            ) {
                l.add(all.get(i));
            }
        }
        return l;


    }

    public  boolean containsIgnoreCase(String str, String searchStr)     {
        if(str == null || searchStr == null) return false;

        final int length = searchStr.length();
        if (length == 0)
            return true;

        for (int i = str.length() - length; i >= 0; i--) {
            if (str.regionMatches(true, i, searchStr, 0, length))
                return true;
        }
        return false;
    }

/**************votre cours*************/
    @GetMapping("/votreCours")
    public String Voscours(Model model,HttpServletRequest httpServletRequest){
        List<Cours> Listcours = coursService.getCoursbyUser(httpServletRequest);
        model.addAttribute("cours", new Cours());
        model.addAttribute("coursfiles",new ArrayList<CoursFiles>());
        // model.addAttribute("isAdd",true);

        List<Filiere> filieres= filiereRepository.findAll();
        model.addAttribute("filieres",filieres);
        model.addAttribute("Listcours",Listcours);
        model.addAttribute("desc","hello");

        return "vosCours";
    }

    @GetMapping(value="/votreCours/search")
    public String VoscoursSearch(Model model, String s,HttpServletRequest httpServletRequest) {
        model.addAttribute("Listcours", this.voscourssSearch(s,httpServletRequest));

        model.addAttribute("cours", new Cours());
        model.addAttribute("coursfiles",new ArrayList<CoursFiles>());
        // model.addAttribute("isAdd",true);

        List<Filiere> filieres= filiereRepository.findAll();
        model.addAttribute("filieres",filieres);
        return "/vosCours";
    }


    public List<Cours> voscourssSearch(String s,HttpServletRequest httpServletRequest) {
        List<Cours> all=  coursService.getCoursbyUser(httpServletRequest);
        ArrayList<Cours> l = new ArrayList<Cours>();
        for(int i=0;i<all.size();i++) {
            if((all.get(i).getTitre().equalsIgnoreCase(s))||(containsIgnoreCase(all.get(i).getTitre(),s))
                    ||(all.get(i).getUser().getNom().equalsIgnoreCase(s))||(containsIgnoreCase(all.get(i).getUser().getNom(),s))
                    ||(all.get(i).getFiliere().getAbr().equalsIgnoreCase(s))||(containsIgnoreCase(all.get(i).getFiliere().getAbr(),s))
                    ||(all.get(i).getUser().getPrenom().equalsIgnoreCase(s))||(containsIgnoreCase(all.get(i).getUser().getPrenom(),s))
                    ||(all.get(i).getFiliere().getNom().equalsIgnoreCase(s))||(containsIgnoreCase(all.get(i).getFiliere().getNom(),s))

            ) {
                l.add(all.get(i));
            }
        }
        return l;


    }

    @GetMapping("/coursParFiliere")
    public String coursParFiliere(Model model){
        model.addAttribute("filieres", filiereRepository.findAll());



        return "CoursParFr";
    }

    @GetMapping("/coursParFiliere/{idF}/cours")
    public String coursfr(@PathVariable Long idF,Model model){
        List<Cours> Listcours = coursService.getCoursByFiliere(idF);
        model.addAttribute("cours", new Cours());
        model.addAttribute("coursfiles",new ArrayList<CoursFiles>());
        // model.addAttribute("isAdd",true);

        List<Filiere> filieres= filiereRepository.findAll();
        model.addAttribute("filieres",filieres);
        model.addAttribute("Listcours",Listcours);

        return "listCoursParFr";
    }


    @GetMapping("/cours/description")
    public String DescriptionCours(@PathVariable Long idC,Model model){
        Cours cours = coursService.findById(idC);


        return "/vosCours";
    }
}
