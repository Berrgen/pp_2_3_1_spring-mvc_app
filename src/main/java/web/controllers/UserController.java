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
    public ModelAndView allUsers() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("users");
        modelAndView.addObject("userList", userService.allUsers());
        return modelAndView;
    }

    @GetMapping(value = "/edit/{id}")
    public ModelAndView edit(@PathVariable(name = "id")long id,@ModelAttribute("user") User user) {
        user = userService.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("edit");
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @PutMapping(value = "/edit/{id}")
    public ModelAndView editUser(@PathVariable(name = "id")long id,@ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        userService.edit(user);
        return modelAndView;
    }

    @DeleteMapping(value = "/delete/{id}")
    public ModelAndView delete(@PathVariable(name = "id")long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        userService.deleteById( id);
        return modelAndView;
    }

    @PostMapping(value = "/add")
    public ModelAndView add(@ModelAttribute(name = "firstname") String firstname,
                            @ModelAttribute(name = "lastname")String lastname,
                            @ModelAttribute(name = "age")int age) {
        userService.add(new User(firstname, lastname, age));
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/");
        return modelAndView;
    }

}
