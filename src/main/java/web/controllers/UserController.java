package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.models.User;
import web.service.UserService;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController (UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/")
    public ModelAndView showAllUsers() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("users");
        modelAndView.addObject("userList", userService.getAllUsers());
        return modelAndView;
    }

    @GetMapping(value = "/edit/{id}")
    public ModelAndView editUser(@PathVariable(name = "id")long id, @ModelAttribute("user") User user) {
        user = userService.getUserById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("edit");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @PutMapping(value = "/edit/{id}")
    public ModelAndView saveUser(@PathVariable(name = "id")long id, @ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        userService.editUser(user);
        return modelAndView;
    }

    @DeleteMapping(value = "/delete/{id}")
    public ModelAndView deleteUser(@PathVariable(name = "id")long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        userService.deleteUserById(id);
        return modelAndView;
    }

    @PostMapping(value = "/add")
    public ModelAndView addUser(@ModelAttribute(name = "firstname") String firstname,
                                @ModelAttribute(name = "lastname")String lastname,
                                @ModelAttribute(name = "age")int age) {
        userService.addUser(new User(firstname, lastname, age));
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }

}
