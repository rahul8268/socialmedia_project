package com.socaiproject.socailproject.controler;

import com.socaiproject.socailproject.modal.Users;
import com.socaiproject.socailproject.service.UserService;
import com.socaiproject.socailproject.service.UserServieImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class UserControler {
    @Autowired
    UserService
            service;

    public UserControler(UserServieImp service) {
        this.service = service;
    }

    @GetMapping("users")
    public List<Users> getUsers(){
        return service.getUsers();
    }

    @PostMapping("users")
    public String insertUsers(@RequestBody Users users){
        return service.insertUsers(users);
    }


}
