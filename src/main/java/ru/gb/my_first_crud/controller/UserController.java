package ru.gb.my_first_crud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.gb.my_first_crud.model.User;
import ru.gb.my_first_crud.service.UserService;

import java.util.List;

@Controller
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String findAll(Model model) {
        List<User> users = userService.findAll();

        model.addAttribute("users", users);
        return "user-list";
        //return "home.html";
    }

    @GetMapping("/user-create")
    public String createUserForm(User user) {
        return "user-create";
    }

    @PostMapping("/user-create")
    public String createUser(User user) {
        userService.saveUser(user);
        return "redirect:/users";
    }


    @GetMapping("/user-delete/{id}")
    //@DeleteMapping("/user-delete/{id}")
    public String deleteUser(@PathVariable int id) {
        userService.deleteById(id);
        return "redirect:/users";
    }

    @GetMapping("/user-update/{id}")
    public String updateUserForm(@PathVariable("id") int id,Model model){
        User user  = userService.getOne(id);
        model.addAttribute("user",user);
        return "user-update";
    }

    @PostMapping("/user-update")
    public String updateUser(@ModelAttribute User user){
        userService.update(user);
        return "redirect:/users";
    }

}
