package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.service.UserService;
import web.service.UserServiceImpl;

import java.util.List;

@Controller
public class UserController {
    UserService service = new UserServiceImpl();
    List list = service.getAllUsers();
    @GetMapping(value = "/")
    public String getUserList(@RequestParam(name = "id",required = false) Long id,
                                  @RequestParam(name = "name",required = false) String name,
                                  @RequestParam(name = "lastName",required = false) String lastname,
                                  @RequestParam(name = "age",required = false) Byte age,
                                  ModelMap model) {

        model.addAttribute("list",list);
        return "users";


    }
}