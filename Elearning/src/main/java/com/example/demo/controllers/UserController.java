package com.example.demo.controllers;

import com.example.demo.entities.Role;
import com.example.demo.entities.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@Controller
public class UserController {


   /* PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @GetMapping("/users")
    public String findAll(Model model/*, @RequestParam(defaultValue = "0") int page *//* ){
       model.addAttribute("roles",roleRepository.findAll());
        model.addAttribute("data", userRepository.findAll(/*PageRequest.of(page,4)*/ /*));
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
*/

    @Autowired
    private UserService userService;
    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/users")
    public String dashboard(Model model) {
        //display all Tasks
        Set<User> users = userService.getUsers();
        model.addAttribute("data", users);

        model.addAttribute("roles", roleRepository.findAll());
        return "users";
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public String createUser(User user){
        User newUser= userService.createUser(user);
        return "redirect:/users";
    }


    @RequestMapping(path = "/update", method = RequestMethod.POST)
    public  String updateUser(User user){
        userService.updateUser(user.getId(),user);
        return "redirect:/users";
    }

    @RequestMapping(path = "/findUser/{id}", method = RequestMethod.GET)
    @ResponseBody
    public User findTask(@PathVariable("id") long id) {
        return userService.findById(id);
    }

    @RequestMapping(path = "/users/{id}/delete", method = RequestMethod.GET)
    public String deleteUser(@PathVariable("id") long id, HttpServletRequest request){
        userService.deleteUser(id);
        return "redirect:/users";
    }


}
