package com.example.demo.controllers;

import com.example.demo.entities.*;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Controller
public class UserController {


@Autowired
private UserRepository userRepository;

    @Autowired
    private UserService userService;
    @Autowired
    private RoleRepository roleRepository;

    @PreAuthorize(value = "hasAuthority('ROLE_ADMIN')")
    @GetMapping("/users")
    public String dashboard(Model model,HttpServletRequest httpServletRequest) {
        //display all Tasks

        List<User> users = userService.getListUsers(httpServletRequest);
        Role role= roleRepository.findById(Long.parseLong("2")).get();
        Set<User> userEtu =userService.getUserByRole(role);
        model.addAttribute("userEtu", userEtu);
        model.addAttribute("roles",roleRepository.findAll());
        model.addAttribute("users", users);


       User u = userService.getUserConnect(httpServletRequest);
        model.addAttribute("usersConn",u);
        return "users";
    }


    @GetMapping("/")
    public String index(Model model,HttpServletRequest httpServletRequest) {

        User u = userService.getUserConnect(httpServletRequest);
        model.addAttribute("usersConn",u);
        return "index";
    }

    @RequestMapping(path = "/users/create", method = RequestMethod.POST)
    public String createUser(User user){
        User newUser= userService.createUser(user);
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
            return "UserUpdate";
        }
        user.setPassword(user.getPassword());
        user.setCreatedDate(new Date());
        userRepository.save(user);
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("roles",roleRepository.findAll());

        redirectAttributes.addFlashAttribute("successmessage","User est modifié");

        return "users";
    }




    @RequestMapping(path = "/users/{id}/delete", method = RequestMethod.GET)
    public String deleteUser(@PathVariable("id") long id, HttpServletRequest request){
        userService.deleteUser(id);
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
