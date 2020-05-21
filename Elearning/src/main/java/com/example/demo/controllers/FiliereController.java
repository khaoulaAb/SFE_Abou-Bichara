package com.example.demo.controllers;


import com.example.demo.entities.Filiere;
import com.example.demo.entities.User;
import com.example.demo.repository.FiliereRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class FiliereController {
    @Autowired
    private UserService userService;
    @Autowired
    private FiliereRepository filiereRepository;

    @GetMapping("/filieres")
    public String index(Model model,HttpServletRequest httpServletRequest) {
        User u = userService.getUserConnect(httpServletRequest);
        model.addAttribute("usersConn",u);
        List<Filiere> filieres=filiereRepository.findAllByAbr();
        model.addAttribute("filieres",filieres);
        return "filieres";
    }


    @GetMapping(value="/filieres/search")
    public String FilieresSearch(Model model, String s) {
        model.addAttribute("filieres", this.filiereSearch(s));
        return "/filieres";
    }


    public List<Filiere> filiereSearch(String s) {
        ArrayList<Filiere> all= (ArrayList<Filiere>) filiereRepository.findAll();
        ArrayList<Filiere> l = new ArrayList<Filiere>();
        for(int i=0;i<all.size();i++) {
            if((all.get(i).getNom().equalsIgnoreCase(s))||(containsIgnoreCase(all.get(i).getNom(),s))
                    ||(all.get(i).getAbr().equalsIgnoreCase(s))||(containsIgnoreCase(all.get(i).getAbr(),s)
            )) {
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



    @PostMapping("/filieres/save")
    public String saveFiliere(@ModelAttribute Filiere filiere, RedirectAttributes redirectAttributes, Model model){
        Filiere newFiliere = filiereRepository.save(filiere);

        if(newFiliere!=null){
            redirectAttributes.addFlashAttribute("successMessage","Filiere est enregistré");
            return "redirect:/filieres";
        }
        else{
            redirectAttributes.addFlashAttribute("errormessage","filiere n'est pas enregistré");
            model.addAttribute("filiere",filiere);
            return "filieres";
        }
    }



    @GetMapping("/filieres/editfiliere/{id}")

    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Filiere filiere = filiereRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        model.addAttribute("filiere", filiere);

        return "filiereUpdate";
    }

    @PostMapping("/filieres/update/{id}")
    public String updateFiliere(@PathVariable("id") long id, @Valid Filiere filiere,
                             BindingResult result, Model model,RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            filiere.setId(id);
            return "filiereUpdate";
        }

        filiereRepository.save(filiere);
        model.addAttribute("filieres", filiereRepository.findAll());
        redirectAttributes.addFlashAttribute("successmessage","Filière est modifié");

        return "filieres";
    }



    @GetMapping("/filieres/deletefiliere/{id}")
    public String deletecours(@PathVariable Long id){
        filiereRepository.deleteById(id);


        return "redirect:/filieres";
    }


}
