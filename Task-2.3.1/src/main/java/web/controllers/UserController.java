package web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.model.User;
import web.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //Get user or users
    @GetMapping()
    public String listCarsByLimit(@RequestParam(value = "count", required = false, defaultValue = "5") int amount, Model model) {
        model.addAttribute("listUsers", userService.getListUserLimit(amount));
        return "users";
    }

    @GetMapping("/{id}")
    public String userById(@PathVariable("id") int id, Model model) {
        User user = userService.getUserById(id);
        System.out.println(user);
        model.addAttribute("getUserById", user);
        return "userById";
    }

    @GetMapping("/getAmountUsers")
    public String showAmountCars(ModelMap model) {
        model.addAttribute("messages", userService.showAmountUsers());
        return "getAmountUsers";
    }

    @GetMapping("/getListUsers")
    public String getListCars(ModelMap model) {
        model.addAttribute("listUser", userService.getListUser());
        return "listUsers";
    }

    //Create
    @PostMapping()
    public String createUser(@ModelAttribute("user") User user, Model model) {
        userService.saveUser(user);
        model.addAttribute("user", user);
        return "successPage";
    }

    @GetMapping("/newUser")
    public String addCar(@ModelAttribute("user") User user) {
        return "/newUser";
    }

    //Update(edit)
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "/edit";
    }

    @PatchMapping("/{id}")
    public String upDate(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userService.updateUser(user, id);
        return "redirect:/users";
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.deleteUser(userService.getUserById(id));
        return "redirect:/users";
    }
}