package com.example.demo.service;

import com.example.demo.entities.Role;
import com.example.demo.entities.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@Controller
@Secured(value = "ROLE_ADMIN")
public class UserRoleService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;
/*
   @PostMapping("/save")
    public String save(User u){

        userRepository.save(u);
        return "redirect:/users";
    }



*/




    /*public String findAll(Model model ){

        model.addAttribute("data", userRepository.findAll());
        return "register";
    }
 /*
    @GetMapping("/delete")
    public String delete(String username){
        User u = userRepository.findById(username).orElse(null);
        userRepository.delete(u);
        return "redirect:/users";
    }

    @GetMapping("/finOne")
    @ResponseBody
    public User findOne(String username){
        return userRepository.findById(username).orElse(null);
    }


    /*******************************
    @RequestMapping(value="/addRole")
    public Role saveRole(Role r){
        return roleRepository.save(r);
    }

    @RequestMapping(value="/findRole")
    public List<Role> findAllRoles(){
        return  roleRepository.findAll();
    }

  /*  @RequestMapping(value="/addRoleToUser")
    public User addRoleToUser(String username, String role){
        User u= userRepository.findById(username).orElse(null);
        Role r= roleRepository.findById(role).orElse(null);
        u.getRoles().add(r);
        userRepository.save(u);
        return u;
    }
*/



}
