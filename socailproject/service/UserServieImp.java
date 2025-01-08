package com.socaiproject.socailproject.service;

import com.socaiproject.socailproject.entity.UsersEntity;
import com.socaiproject.socailproject.modal.Users;
import com.socaiproject.socailproject.repositry.UsersRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServieImp implements UserService{
   UsersRepo repo;

    public UserServieImp(UsersRepo repo) {
        this.repo = repo;
    }

    @Override
    public List<Users> getUsers() {
        List<UsersEntity>entityList=repo.findAll();
        List<Users>usersList=new ArrayList<>();
        for (UsersEntity entity:entityList){
            Users users=new Users();
            users.setId(entity.getId());
            users.setUserName(entity.getUserName());
            users.setEmail(entity.getEmail());
            users.setUid(entity.getUid());
            users.setPassword(entity.getPassword());

            usersList.add(users);
        }

        return usersList;
    }

    @Override
    public String insertUsers(Users users) {
        UsersEntity entity=new UsersEntity();
        BeanUtils.copyProperties(users,entity);
        repo.save(entity);

        return "saved success";
    }
}
