package com.example.demo.controllers;

import antlr.StringUtils;
import com.example.demo.entities.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping(value ={ "", "users" })
public class UserController {
    PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @GetMapping("/users")
    public String findAll(Model model/*, @RequestParam(defaultValue = "0") int page */){
        model.addAttribute("roles",roleRepository.findAll());
        model.addAttribute("data", userRepository.findAll(/*PageRequest.of(page,4)*/));
       // model.addAttribute("currentPage", page);
        return "users";
    }

    @PostMapping("/save")
    public String save(@Valid  User user, BindingResult bindingResult, Model model){
        user.setActived(true);
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        model.addAttribute("user",user);
        return "redirect:/users";
    }
    @GetMapping("/delete")
    public String deleteCountry(Long id){
        userRepository.deleteById(id);

        return "redirect:/users";
    }
    @GetMapping("/findOne")
    @ResponseBody
    public User findOne(Long id){
        return userRepository.findById(id).get();
    }




}
