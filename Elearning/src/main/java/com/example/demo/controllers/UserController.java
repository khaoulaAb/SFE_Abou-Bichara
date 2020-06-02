package com.example.demo.controllers;

import com.example.demo.entities.*;
import com.example.demo.repository.CoursRepository;
import com.example.demo.repository.FiliereRepository;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.CoursService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.util.*;

@Controller
public class UserController {


@Autowired
private UserRepository userRepository;

    @Autowired
    private UserService userService;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private CoursRepository coursRepository;

    @Autowired
    private CoursService coursService;

    @Autowired
    private FiliereRepository filiereRepository;


    @PreAuthorize(value = "hasAuthority('ROLE_ADMIN')")
    @GetMapping("/users")
    public String dashboard(Model model,HttpServletRequest httpServletRequest) {
        //display all Tasks

        List<User> users = userService.getListUsers(httpServletRequest);

        model.addAttribute("roles",roleRepository.findAll());
        model.addAttribute("users", users);


       User u = userService.getUserConnect(httpServletRequest);
        model.addAttribute("usersConn",u);
        return "users";
    }


    @GetMapping("/")
    public String index(Model model,HttpServletRequest httpServletRequest) {

        User u = userService.getUserConnect(httpServletRequest);
        Set<User> userEtu =userService.getUserByRole(roleRepository.findById(Long.parseLong("3")).get());
        model.addAttribute("userEtu", userEtu);

        Set<User> userProf =userService.getUserByRole(roleRepository.findById(Long.parseLong("2")).get());
        model.addAttribute("userProf", userProf);
        model.addAttribute("cours", coursRepository.findAll());
        model.addAttribute("filieres", filiereRepository.findAll());


        /******* Etudiants et Professeurs */
        Map<String, Integer> graphData = new TreeMap<>();
        graphData.put("Professeurs", userProf.size());
        graphData.put("Etudiants", userEtu.size());
        model.addAttribute("chartData", graphData);

        /* F et M dans les Etudiants */
        int f=0;
        int m=0;
        for ( User EtuF: userEtu ){
            if(EtuF.getGenre().equals("F")){
                f++;
            }
            else m++;
        }

        Map<String, Integer> graphData2 = new TreeMap<>();
        graphData2.put("Etudiantes", f);
        graphData2.put("Etudiants", m);
        model.addAttribute("chartData2", graphData2);


        Map<String, Integer> graphData3 = new TreeMap<>();
        for(Filiere filiere: filiereRepository.findAll()){
            graphData3.put(filiere.getAbr(),filiere.getCours().size());
        }
        model.addAttribute("chartData3", graphData3);



        return "index";
    }

    @RequestMapping(path = "/users/create", method = RequestMethod.POST)
    public String createUser(User user,RedirectAttributes redirectAttributes){
        User newUser= userService.createUser(user);
        redirectAttributes.addFlashAttribute("successmessage","Utilisateur a été ajouté");

        return "redirect:/users";
    }


    @GetMapping("/users/findUser/{id}")
    public String edituser(@PathVariable Long id, Model model){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        model.addAttribute("user", user);
        model.addAttribute("roles",roleRepository.findAll());

        return "UserUpdate";
    }



    @PostMapping("/users/update/{id}")
    public String updateUser(@PathVariable("id") long id, @Valid User user,
                                BindingResult result, Model model,RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            user.setId(id);
            model.addAttribute("roles",roleRepository.findAll());
            return "UserUpdate";
        }

        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();


        user.setPassword(encoder.encode(user.getPassword()));

        user.setCreatedDate(new Date());
        userRepository.save(user);
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("roles",roleRepository.findAll());

        redirectAttributes.addFlashAttribute("successmessage","Utilisateur a été modifié");

        return "redirect:/users";
    }




    @RequestMapping(path = "/users/{id}/delete", method = RequestMethod.GET)
    public String deleteUser(@PathVariable("id") long id, HttpServletRequest request,RedirectAttributes redirectAttributes){
        List<Cours> cours= coursService.getCoursByUser(id);
        if(cours!=null) {

                for (Cours c : cours) {
                    if (c.getRemarques() != null) {
                        coursService.deleteRemarquesByCours(c.getId());
                    }
                    if (c.getFiles() != null) {
                        coursService.deleteFilesByCoursId(c.getId());
                    }
                    coursService.deleteCours(c.getId());
                }
            }

                userService.deleteUser(id);


            redirectAttributes.addFlashAttribute("successmessage","Utilisateur a été supprimé");



        return "redirect:/users";
    }



    @GetMapping(value="/users/search")
    public String UsersSearch(Model model, String s,HttpServletRequest httpServletRequest) {
        model.addAttribute("users", this.userSearch(s,httpServletRequest));
        return "/users";
    }


    public List<User> userSearch(String s,HttpServletRequest httpServletRequest) {
        ArrayList<User> all= (ArrayList<User>) userService.getListUsers(httpServletRequest);
        ArrayList<User> l = new ArrayList<User>();
        for(int i=0;i<all.size();i++) {
            if((all.get(i).getNom().equalsIgnoreCase(s))||(containsIgnoreCase(all.get(i).getNom(),s))
                    ||(all.get(i).getNom().equalsIgnoreCase(s))||(containsIgnoreCase(all.get(i).getNom(),s))
                    ||(all.get(i).getPrenom().equalsIgnoreCase(s))||(containsIgnoreCase(all.get(i).getPrenom(),s))
                    ||(all.get(i).getRole().getRole().equalsIgnoreCase(s))||(containsIgnoreCase(all.get(i).getRole().getRole(),s))
                    ||(all.get(i).getEmail().equalsIgnoreCase(s))||(containsIgnoreCase(all.get(i).getEmail(),s))
                    ||(all.get(i).getGenre().equalsIgnoreCase(s))||(containsIgnoreCase(all.get(i).getGenre(),s))

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




}
