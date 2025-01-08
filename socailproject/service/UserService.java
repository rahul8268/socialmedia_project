package com.socaiproject.socailproject.service;

import com.socaiproject.socailproject.modal.Users;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserService {

    List<Users>getUsers();
    String insertUsers(Users users);
}
